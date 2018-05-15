package lokalspots.property.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "l0kalsp0ts_FacilityType") 
public class FacilityType {
	
	private Long facilityTypeId;
	private String facilityTypeName;
	
	public Set<FacilityTypeFacility> facilityTypeFacility = new HashSet<FacilityTypeFacility>();
	
	public FacilityType(){}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getFacilityTypeId() {
		return facilityTypeId;
	}
	public void setFacilityTypeId(Long facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}
	public String getFacilityTypeName() {
		return facilityTypeName;
	}
	public void setFacilityTypeName(String facilityTypeName) {
		this.facilityTypeName = facilityTypeName;
	}

	@OneToMany(mappedBy = "facilityType")
	public Set<FacilityTypeFacility> getFacilityTypeFacility() {
		return facilityTypeFacility;
	}

	public void setFacilityTypeFacility(Set<FacilityTypeFacility> facilityTypeFacility) {
		this.facilityTypeFacility = facilityTypeFacility;
	}

	
     
}
