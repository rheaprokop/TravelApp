package lokalspots.property.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "l0kalsp0ts_Facility") 
public class Facility {
	
	private Long facilityId;
	private String facilityName;
	private String facilityIconName; 
	private Date facilityCreatedDate;
    private Date facilityLastUpdateDate;   
    
	public Set<PropertyFacility> propertyFacility = new HashSet<PropertyFacility>();
	public Set<FacilityTypeFacility> facilityTypeFacility = new HashSet<FacilityTypeFacility>();
	
    public Facility(){}
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFacilityIconName() {
		return facilityIconName;
	}
	public void setFacilityIconName(String facilityIconName) {
		this.facilityIconName = facilityIconName;
	}

	public Date getFacilityCreatedDate() {
		return facilityCreatedDate;
	}
	public void setFacilityCreatedDate(Date facilityCreatedDate) {
		this.facilityCreatedDate = facilityCreatedDate;
	}
	public Date getFacilityLastUpdateDate() {
		return facilityLastUpdateDate;
	}
	public void setFacilityLastUpdateDate(Date facilityLastUpdateDate) {
		this.facilityLastUpdateDate = facilityLastUpdateDate;
	}

	@OneToMany(mappedBy = "facility")
	public Set<PropertyFacility> getPropertyFacility() {
		return propertyFacility;
	}

	public void setPropertyFacility(Set<PropertyFacility> propertyFacility) {
		this.propertyFacility = propertyFacility;
	}

	@OneToMany(mappedBy = "facility")
	public Set<FacilityTypeFacility> getFacilityTypeFacility() {
		return facilityTypeFacility;
	}

	public void setFacilityTypeFacility(Set<FacilityTypeFacility> facilityTypeFacility) {
		this.facilityTypeFacility = facilityTypeFacility;
	}
	
}
