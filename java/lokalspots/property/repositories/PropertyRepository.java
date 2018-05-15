package lokalspots.property.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lokalspots.property.models.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	//LATEST PROPERTIES
	@Query("SELECT p FROM Property p WHERE isDraft = false ORDER BY p.createDate DESC")
	List<Property> findLatest5Posts(Pageable pageable);
	
	@Transactional
	@Query("SELECT p FROM Property p WHERE p.draft=:draft AND p.propertyOwnerAsMember =:propertyOwnerAsMember  ORDER BY p.createDate DESC")
	List<Property> findAllUnfinishedPropertyList(@Param("draft") Boolean draft,
			@Param("propertyOwnerAsMember") String propertyOwnerAsMember);
	
	//DETAILS
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Property p SET p.propertyTitle=:propertyTitle, p.propertyLongDescription=:propertyLongDescription, "
			+ "p.lastUpdatedDate=:lastUpdatedDate where p.propertyId=:propertyId")
	void saveDetail(@Param("propertyId") Long propertyId, @Param("propertyTitle") String propertyTitle,
				@Param("propertyLongDescription") String propertyLongDescription, @Param("lastUpdatedDate") Date lastUpdatedDate);
	
	//LOCATION
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Property p SET p.propertyAddress1=:propertyAddress1, p.propertyAddressNo=:propertyAddressNo,p.propertyCity=:propertyCity,"
			+ "p.propertyState=:propertyState,p.propertyCountry=:propertyCountry,p.propertyZipCode=:propertyZipCode,"
			+ "p.propertyMainPhone=:propertyMainPhone,p.propertyEmailAddress=:propertyEmailAddress, p.lastUpdatedDate=:lastUpdatedDate   where p.propertyId=:propertyId")
	void savelocation(@Param("propertyId") Long propertyId, @Param("propertyAddress1") String propertyAddress1, 
			@Param("propertyAddressNo") String propertyAddressNo, @Param("propertyCity") String propertyCity, @Param("propertyState") String propertyState,
			@Param("propertyCountry") String propertyCountry, @Param("propertyZipCode") String propertyZipCode,
			@Param("propertyMainPhone") String propertyMainPhone, @Param("propertyEmailAddress") String propertyEmailAddress, @Param("lastUpdatedDate") Date lastUpdatedDate);
	
	//PHOTO
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Property p SET p.fileUploadPrimaryImageId=:fileUploadPrimaryImageId, p.lastUpdatedDate=:lastUpdatedDate  where p.propertyId=:propertyId")
	void savePrimaryImage(@Param("fileUploadPrimaryImageId") Long fileUploadPrimaryImageId, @Param("propertyId") Long propertyId, @Param("lastUpdatedDate") Date lastUpdatedDate);

	//BEDROOMS
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Property p SET p.propertyNoOfGuests=:propertyNoOfGuests, p.propertyNoOfBathrooms=:propertyNoOfBathrooms, p.propertyNoOfBedrooms=:propertyNoOfBedrooms, "
			+ "p.propertyBedrooms=:propertyBedrooms WHERE p.propertyId=:propertyId")
	void saveAccommodations(@Param("propertyNoOfGuests") String propertyNoOfGuests, @Param("propertyNoOfBathrooms") String propertyNoOfBathrooms, @Param("propertyNoOfBedrooms") String propertyNoOfBedrooms, 
			@Param("propertyBedrooms") String propertyBedrooms, @Param("propertyId") Long propertyId); 
	
	//PRICING
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Property p SET p.propertyBasePrice=:propertyBasePrice, p.propertyCurrency=:propertyCurrency,p.propertyDiscounts=:propertyDiscounts,"
			+ "p.lastUpdatedDate=:lastUpdatedDate where p.propertyId=:propertyId")
	void savePricing(@Param("propertyBasePrice") String propertyBasePrice, @Param("propertyCurrency") String propertyCurrency, 
					@Param("propertyDiscounts") String propertyDiscounts, @Param("lastUpdatedDate") Date lastUpdatedDate, @Param("propertyId") Long propertyId);
	
	//PROPERTY SAVE AND SUBMIT
		@Transactional
		@Modifying(clearAutomatically = true)	
		@Query("UPDATE Property p SET p.draft=:isDraft, p.lastUpdatedDate=:lastUpdatedDate  where p.propertyId=:propertyId")
		void saveAndSubmit(@Param("propertyId") Long propertyId, @Param("isDraft") Boolean isDraft, @Param("lastUpdatedDate") Date lastUpdatedDate);

}
