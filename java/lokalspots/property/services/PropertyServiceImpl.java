package lokalspots.property.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import lokalspots.property.models.Property;
import lokalspots.property.repositories.PropertyRepository;

@Service
@Primary
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	private PropertyRepository propertyRepo; 
	
	@Override
	public Property findById(Long propertyId) {
	return this.propertyRepo.findOne(propertyId);
	}
	
	@Override
	public List<Property> findAllUnfinishedPropertyList(Boolean draft, String propertyOwnerAsMember) {
		return propertyRepo.findAllUnfinishedPropertyList(draft, propertyOwnerAsMember);
	}
	
	@Override
	public void save(Property property) { 
		propertyRepo.save(property);
	} 
	
	@Override
	public void saveDetail(Long propertyId, String propertyTitle, String propertyLongDescription, Date lastUpdatedDate ) { 
		propertyRepo.saveDetail(propertyId, propertyTitle, propertyLongDescription, lastUpdatedDate);
	}
	
	@Override
	public void saveLocation(Long propertyId, String propertyAddress1, String propertyAddressNo,
			String propertyCity, String propertyState, String propertyCountry, String propertyZipCode,
			String propertyMainPhone, String propertyEmailAddress, Date lastUpdatedDate ) { 
		propertyRepo.savelocation(propertyId, propertyAddress1, propertyAddressNo, propertyCity, propertyState, propertyCountry, 
				propertyZipCode, propertyMainPhone, propertyEmailAddress, lastUpdatedDate);
	}
	
	@Override
	public void savePrimaryImage(Long fileUploadPrimaryImageId, Long propertyId, Date lastUpdatedDate ) { 
		propertyRepo.savePrimaryImage(fileUploadPrimaryImageId, propertyId, lastUpdatedDate);
	}
	
	@Override
	public void saveAccommodations(String propertyNoOfGuests, String propertyNoOfBathrooms, String propertyNoOfBedrooms,String propertyBedrooms, Long propertyId) {
		propertyRepo.saveAccommodations(propertyNoOfGuests, propertyNoOfBathrooms, propertyNoOfBedrooms, propertyBedrooms, propertyId);
	}
	
	@Override
	public void savePricing(String propertyBasePrice,String propertyCurrency, String propertyDiscounts, Date lastUpdatedDate, Long propertyId) {
		propertyRepo.savePricing(propertyBasePrice, propertyCurrency, propertyDiscounts, lastUpdatedDate, propertyId);
	}
	
	@Override
	public void saveAndSubmit(Long propertyId, Boolean isDraft, Date lastUpdatedDate) { 
		propertyRepo.saveAndSubmit(propertyId, isDraft, lastUpdatedDate);
		
	}
	
}
