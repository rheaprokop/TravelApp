package lokalspots.property.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lokalspots.auth.services.SecurityService;
import lokalspots.global.models.FileUpload;
import lokalspots.global.services.FileUploadService;
import lokalspots.property.models.Facility;
import lokalspots.property.models.FacilityService;
import lokalspots.property.models.Property;
import lokalspots.property.models.PropertyFacility;
import lokalspots.property.models.PropertyType;
import lokalspots.property.services.PropertyFacilityService;
import lokalspots.property.services.PropertyService;
import lokalspots.property.services.PropertyTypeService;

@CrossOrigin
@Controller
public class PropertyController {

	@Autowired
	private PropertyTypeService propertyTypeService;
	@Autowired
	private PropertyService propertyService;

	// auth
	@Autowired
	private SecurityService securityService;

	// file
	@Autowired
	FileUploadService fileUploadService;

	@Autowired
	FacilityService facilityService; 
	
	@Autowired
	PropertyFacilityService propertyFacilityService;
	
	@RequestMapping("/property/hotel/list")
	public String list() {
		return "site/property/list";
	}

	@RequestMapping("/property/detail/{propertyId}")
	public String detail() {
		return "site/property/detail";
	}

	// CREATE PROPERTY FOR FIRST TIME - VIEW
	@RequestMapping("/explore/property/add")
	public String add(Model model) {

		// List all property types
		List<PropertyType> propertyType = propertyTypeService.findAll();
		model.addAttribute("propertyType", propertyType);

		return "site/property/add";
	}

	// CREATE PROPERTY FOR FIRST TIME - POST
	@RequestMapping(value = "/explore/property/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("propertyForm") Property propertyForm, BindingResult bindingResult, Model model) {
		propertyForm.setPropertyOwnerAsMember(securityService.findLoggedInUsername());
		propertyForm.setCreateDate(new Date());
		propertyForm.setDraft(true);
		propertyService.save(propertyForm);

		return "redirect:/member/property/detail/" + propertyForm.getPropertyId();
	}

	// MEMBER CONTROLLERS

	@RequestMapping("/member/dashboard")
	public String memberDashboard(Model model) {
		return "member/dashboard";
	}

	// DETAIL VIEW
	@RequestMapping("/member/property/detail/{propertyId}")
	public String memberPropertyDetail(@PathVariable("propertyId") Long propertyId, Model model) {
		Property propertyDetail = propertyService.findById(propertyId);
		model.addAttribute("propertyDetail", propertyDetail);

		return "member/property/detail";
	}

	// DETAIL POST
	@RequestMapping(value = "/member/property/detail", method = RequestMethod.POST)
	public String memberPropertyDetail(@RequestParam("propertyId") Long propertyId,
			@ModelAttribute("propertyForm") Property propertyForm) {
		propertyForm.setLastUpdatedDate(new Date());
		propertyForm.setDraft(true);
		propertyService.saveDetail(propertyForm.getPropertyId(), propertyForm.getPropertyTitle(),
				propertyForm.getPropertyLongDescription(), new Date());
		return "redirect:/member/property/location/" + propertyForm.getPropertyId();
	}

	// LOCATION VIEW
	@RequestMapping("/member/property/location/{propertyId}")
	public String memberPropertyLocation(@PathVariable("propertyId") Long propertyId, Model model) {
		model.addAttribute("propertyId", propertyId);

		Property propertyDetail = propertyService.findById(propertyId);
		model.addAttribute("propertyDetail", propertyDetail);

		return "member/property/location";
	}

	// LOCATION POST
	@RequestMapping(value = "/member/property/location", method = RequestMethod.POST)
	public String memberPropertylocation(@RequestParam("propertyId") Long propertyId,
			@ModelAttribute("propertyForm") Property propertyForm) {
		propertyForm.setDraft(true);
		propertyService.saveLocation(propertyForm.getPropertyId(), propertyForm.getPropertyAddress1(),
				propertyForm.getPropertyAddressNo(), propertyForm.getPropertyCity(), propertyForm.getPropertyState(),
				propertyForm.getPropertyCountry(), propertyForm.getPropertyZipCode(),
				propertyForm.getPropertyMainPhone(), propertyForm.getPropertyEmailAddress(), new Date());
		return "redirect:/member/property/photo/" + propertyForm.getPropertyId();
	}

	// PHOTO UPLOAD VIEW
	@RequestMapping("/member/property/photo/{propertyId}")
	public String memberPropertyUpload(@PathVariable("propertyId") Long propertyId, Model model) {
		Property propertyDetail = propertyService.findById(propertyId);
		model.addAttribute("propertyDetail", propertyDetail);

		List<FileUpload> fileRefsIds = fileUploadService.findByTblColumnRefUniqueID(propertyId);
		model.addAttribute("fileRefsIds", fileRefsIds);
		return "member/property/photo";
	}

	// PHOTO UPLOAD POST
	@RequestMapping(value = "/member/property/photo", method = RequestMethod.POST)
	public String memberPropertyUpload(MultipartHttpServletRequest request,
			@ModelAttribute("Property") Property propertyForm, BindingResult bindingResult, Model model) {
		try {

			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				FileUpload newFile = new FileUpload(filename, bytes, mimeType, "Property", "propertyId",
						propertyForm.getPropertyId());

				fileUploadService.uploadFile(newFile);
			}
		} catch (Exception e) {
			return "redirect:/member/property/photo/" + propertyForm.getPropertyId();
		}

		return "redirect:/member/property/accommodation/" + propertyForm.getPropertyId();
	}

	// PHOTO SINGLE VIEW
	@RequestMapping(value = "/member/property/getImg/{Id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> ListImage(@PathVariable long Id) throws IOException {
		FileUpload f = fileUploadService.findById(Id);
		byte[] image = f.getFile();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);

	}

	// PHOTO SET MAIN IMAGE
	@RequestMapping("/member/property/photo/set/image/{imageId}/{propertyId}")
	public String setMainImage(@PathVariable("imageId") Long imageId, @PathVariable("propertyId") Long propertyId) {
		propertyService.savePrimaryImage(imageId, propertyId, new Date());
		return "redirect:/member/property/photo/" + propertyId;
	}

	// PHOTO DELETE FROM LIST
	@RequestMapping("/member/property/photo/delete/image/{imageId}/{propertyId}")
	public String deleteImage(@PathVariable("imageId") Long imageId, @PathVariable("propertyId") Long propertyId) {
		fileUploadService.deleteById(imageId);
		return "redirect:/member/property/photo/" + propertyId;
	}
	
	// ACCOMMODATION VIEW
	@RequestMapping("/member/property/accommodation/{propertyId}")
	public String memberPropertyAccommodation(@PathVariable("propertyId") Long propertyId, Model model) {
		
		Property propertyDetail = propertyService.findById(propertyId);  
		model.addAttribute("propertyDetail", propertyDetail);
		return "member/property/accommodation";
	}

	// ACCOMMODATION POST
	@RequestMapping(value = "/member/property/accommodation", method = RequestMethod.POST)
	public String memberPropertyAccommodation(Long propertyId,
			@RequestParam("bedroom") List<String> bedroom_no,
			@RequestParam("bedroom_single") List<String> bedroom_single, 
			@RequestParam("bedroom_double") List<String> bedroom_double,
			@ModelAttribute("propertyForm") Property propertyForm, BindingResult bindingResult) {
		
		List<String> propertyBedrooms = new ArrayList<String>(); 
		
			for (String bedno : bedroom_no) { 
				propertyBedrooms.add(bedno);
			}
			for (String single : bedroom_single) {
				propertyBedrooms.add("Single_" + single);
			}
			
			for (String doublebed : bedroom_double) {
				propertyBedrooms.add("Double_" + doublebed + "");
			}
			
		propertyService.saveAccommodations(propertyForm.getPropertyNoOfGuests(), propertyForm.getPropertyNoOfBathrooms(), 
					propertyForm.getPropertyNoOfBedrooms(), propertyBedrooms.toString(), propertyId);
		return "redirect:/member/property/amenities/" + propertyForm.getPropertyId();
	}
 
	// AMENITIES VIEW 
	@RequestMapping("/member/property/amenities/{propertyId}")
	public String memberPropertyAmenities(@PathVariable("propertyId") Long propertyId, Model model) {
		
		Property propertyDetail = propertyService.findById(propertyId);  
		model.addAttribute("propertyDetail", propertyDetail);
		
		List<Facility> facilities = facilityService.findAll();
		model.addAttribute("facilities", facilities);
		return "member/property/amenities";
	}
	
	//AMENITIES POST
	@RequestMapping(value="/member/property/amenities", method=RequestMethod.POST)
	public String memberPropertyAmenities(@ModelAttribute("propertyForm") Property propertyForm, BindingResult bindingResult, Model model, 
			@RequestParam("facilityList") Facility[] facilityList) { 
		
		Set<PropertyFacility> propertyFacility = new HashSet<PropertyFacility>();
		
		for (Facility selectedFacility: facilityList) {			
			PropertyFacility newPropertyFacility = new PropertyFacility();  
			newPropertyFacility.setProperty(propertyForm);
			newPropertyFacility.setFacility(selectedFacility);
			newPropertyFacility.setCreatedDate(new Date());
			propertyFacility.add(newPropertyFacility);  
		}
		propertyFacilityService.save(propertyFacility);
		
		return "redirect:/member/property/pricing/" + propertyForm.getPropertyId();
	}

	//PRICE VIEW 
	@RequestMapping("/member/property/pricing/{propertyId}")
	public String memberPropertyPricing(@PathVariable("propertyId") Long propertyId, Model model) {
		Property propertyDetail = propertyService.findById(propertyId);  
		model.addAttribute("propertyDetail", propertyDetail);
		model.addAttribute("isPropertyReady", isPropertyReady(propertyId));
		
		return "member/property/pricing";
	}
	
	//PRICE POST
	@RequestMapping(value="/member/property/pricing", method=RequestMethod.POST)
	public String memberPropertyPricing(@ModelAttribute("propertyForm") Property propertyForm, BindingResult bindingResult, Model model,
			@RequestParam("discount") List<String> discount) { 
		List<String> discounts = new ArrayList<String>(); 
		for(String d : discount) {
			discounts.add(d);
		}
		 
		propertyService.savePricing(propertyForm.getPropertyBasePrice(), propertyForm.getPropertyCurrency(), 
				discounts.toString(), new Date(), propertyForm.getPropertyId());
		return "redirect:/member/property/finish/" + propertyForm.getPropertyId();
	}
	
	// FINISH VIEW
	@RequestMapping("/member/property/finish/{propertyId}")
	public String memberPropertyFinish(@PathVariable("propertyId") Long propertyId, Model model) {
		Property propertyDetail = propertyService.findById(propertyId);  
		model.addAttribute("propertyDetail", propertyDetail);
		model.addAttribute("isPropertyReady", isPropertyReady(propertyId));
		
		return "member/property/finish";
	}

	// FINISH POST
	@RequestMapping(value="/member/property/finish/{propertyId}", method=RequestMethod.POST)
	public String memberPropertyFinish(@PathVariable("propertyId") Long propertyId, 
			@ModelAttribute("propertyForm") Property propertyForm, BindingResult bindingResult, Model model) {
		Property propertyDetail = propertyService.findById(propertyId);  
		model.addAttribute("propertyDetail", propertyDetail); 
		
		propertyService.saveAndSubmit(propertyForm.getPropertyId(), false, new Date());
		return "redirect:/property/detail/"  + propertyForm.getPropertyId();
	}
	
	// Property populate country List
	@ModelAttribute("countryList")
	public Map<String, String> getCountryList() {
		String[] locales = Locale.getISOCountries();
		Map<String, String> countryList = new HashMap<String, String>();
		for (String countryCode : locales) {
			Locale locale = new Locale("", countryCode);
			countryList.put(locale.getCountry(), locale.getDisplayCountry());
		}
		return countryList;
	}

	// UNFINISHED LIST
	@RequestMapping("/member/property/unfinished")
	public String memberUnfinishedList(Model model) {
		List<Property> drafts = propertyService
				.findAllUnfinishedPropertyList(true,"rhea@omgeez.com");
		
		System.out.println("count111" + drafts.size());
		model.addAttribute("draftProperties", drafts);
		return "member/property/property-unfinished";
	}
	
	private boolean isPropertyReady(Long propertyId) {
		Property p = propertyService.findById(propertyId);  
		
		if(p.getPropertyTitle() != null && p.getPropertyAddress1() != null && p.getPropertyLongDescription() != null && p.getPropertyCity() != null &&
		   p.getPropertyCountry() != null && p.getPropertyEmailAddress() != null && p.getPropertyMainPhone() != null && p.getFileUploadPrimaryImageId() != null && 
		   p.getPropertyBasePrice() != null && p.getPropertyCurrency() != null && propertyFacilityService.findByPropertyId(propertyId).size() >= 1) { 
			return true;
		} else { 
			return false;
		}
	}
	// Populate unfinished property listing
	@ModelAttribute("countUnfinished")
	public String getUnfinishedList() {

		List<Property> countUnfinished = propertyService
				.findAllUnfinishedPropertyList(true,"rhea@omgeez.com");
		return Integer.toString(countUnfinished.size());
	}
	
	
}
