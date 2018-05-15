package lokalspots.property.models;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "l0kalsp0ts_Property")
public class Property {

	private Long propertyId;
	private Long propertyType;
	private String propertyTitle;
	@Lob
	@Column(nullable = false, length = 5000)
	@Length(max = 5000)
	private String propertyLongDescription;
	private String propertyAddress1;
	private String propertyAddressNo;
	private String propertyCity;
	private String propertyState;
	private String propertyCountry;
	private String propertyZipCode;
	private String propertyMainPhone;
	private String propertyEmailAddress;
	// A user in the system with a role "MEMBER"
	private String propertyOwnerAsMember;
	private String propertyNoOfGuests;
	private String propertyNoOfBathrooms;
	private String propertyNoOfBedrooms;
	@Lob
	@Column(nullable = false, length = 1000)
	@Length(max = 1000)
	private String propertyBedrooms; 
	private String propertyBasePrice;
	private String propertyCurrency; 
	@Lob
	@Column(nullable = false, length = 1000)
	@Length(max = 1000)
	private String propertyDiscounts;
	private Long fileUploadPrimaryImageId; // relations FileUpload.Id
	private boolean isDraft;
	private Date createDate;
	private Date lastUpdatedDate;

	private Set<PropertyFacility> propertyFacility;

	public Property() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public Long getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Long propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public String getPropertyLongDescription() {
		return propertyLongDescription;
	}

	public void setPropertyLongDescription(String propertyLongDescription) {
		this.propertyLongDescription = propertyLongDescription;
	}

	public String getPropertyAddress1() {
		return propertyAddress1;
	}

	public void setPropertyAddress1(String propertyAddress1) {
		this.propertyAddress1 = propertyAddress1;
	}

	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public String getPropertyCountry() {
		return propertyCountry;
	}

	public void setPropertyCountry(String propertyCountry) {
		this.propertyCountry = propertyCountry;
	}

	public String getPropertyAddressNo() {
		return propertyAddressNo;
	}

	public void setPropertyAddressNo(String propertyAddressNo) {
		this.propertyAddressNo = propertyAddressNo;
	}

	public String getPropertyZipCode() {
		return propertyZipCode;
	}

	public void setPropertyZipCode(String propertyZipCode) {
		this.propertyZipCode = propertyZipCode;
	}

	public String getPropertyMainPhone() {
		return propertyMainPhone;
	}

	public void setPropertyMainPhone(String propertyMainPhone) {
		this.propertyMainPhone = propertyMainPhone;
	}

	public String getPropertyEmailAddress() {
		return propertyEmailAddress;
	}

	public void setPropertyEmailAddress(String propertyEmailAddress) {
		this.propertyEmailAddress = propertyEmailAddress;
	}

	public String getPropertyOwnerAsMember() {
		return propertyOwnerAsMember;
	}

	public void setPropertyOwnerAsMember(String propertyOwnerAsMember) {
		this.propertyOwnerAsMember = propertyOwnerAsMember;
	}

	public String getPropertyNoOfGuests() {
		return propertyNoOfGuests;
	}

	public void setPropertyNoOfGuests(String propertyNoOfGuests) {
		this.propertyNoOfGuests = propertyNoOfGuests;
	}

	public String getPropertyNoOfBathrooms() {
		return propertyNoOfBathrooms;
	}

	public void setPropertyNoOfBathrooms(String propertyNoOfBathrooms) {
		this.propertyNoOfBathrooms = propertyNoOfBathrooms;
	}

	public String getPropertyNoOfBedrooms() {
		return propertyNoOfBedrooms;
	}

	public void setPropertyNoOfBedrooms(String propertyNoOfBedrooms) {
		this.propertyNoOfBedrooms = propertyNoOfBedrooms;
	}

	public String getPropertyBedrooms() {
		return propertyBedrooms;
	}

	public void setPropertyBedrooms(String propertyBedrooms) {
		this.propertyBedrooms = propertyBedrooms;
	}

	public String getPropertyBasePrice() {
		return propertyBasePrice;
	}

	public void setPropertyBasePrice(String propertyBasePrice) {
		this.propertyBasePrice = propertyBasePrice;
	}

	public String getPropertyCurrency() {
		return propertyCurrency;
	}

	public void setPropertyCurrency(String propertyCurrency) {
		this.propertyCurrency = propertyCurrency;
	}

	public String getPropertyDiscounts() {
		return propertyDiscounts;
	}

	public void setPropertyDiscounts(String propertyDiscounts) {
		this.propertyDiscounts = propertyDiscounts;
	}

	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}

	public Long getFileUploadPrimaryImageId() {
		return fileUploadPrimaryImageId;
	}

	public void setFileUploadPrimaryImageId(Long fileUploadPrimaryImageId) {
		this.fileUploadPrimaryImageId = fileUploadPrimaryImageId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	public Set<PropertyFacility> getPropertyFacility() {
		return propertyFacility;
	}

	public void setPropertyFacility(Set<PropertyFacility> propertyFacility) {
		this.propertyFacility = propertyFacility;
	}

}
