package lokalspots.property.services;

import java.util.Date;
import java.util.List;

import lokalspots.property.models.Property;

public interface PropertyService {
	
	Property findById(Long propertyId);
	
 	List<Property> findAllUnfinishedPropertyList(Boolean draft, String propertyOwnerAsMember);
	
	void save(Property property);
	
	void saveDetail(Long propertyId, String propertyTitle, String propertyLongDescription, Date lastUpdatedDate );
	void saveLocation(Long propertyId, String propertyAddress1, String propertyAddressNo,
			String propertyCity, String propertyState, String propertyCountry, String propertyZipCode,
			String propertyMainPhone, String propertyEmailAddress, Date lastUpdatedDate);
	
	void savePrimaryImage(Long fileUploadPrimaryImageId, Long propertyId,  Date lastUpdatedDate);
	void saveAccommodations(String propertyNoOfGuests, String propertyNoOfBathrooms, String propertyNoOfBedrooms,String propertyBedrooms, Long propertyId);
	void savePricing(String propertyBasePrice,String propertyCurrency, String propertyDiscounts, Date lastUpdatedDate, Long propertyId);
	void saveAndSubmit(Long propertyId, Boolean isDraft, Date lastUpdatedDate);
}
