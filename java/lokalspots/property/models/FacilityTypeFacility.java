package lokalspots.property.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "l0kalsp0ts_FacilityTypeFacility") 
public class FacilityTypeFacility {

	private Long facilityTypeFacilityId;
	private FacilityType facilityType; 
	private Facility facility; 
	private Date createdDate;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getFacilityTypeFacilityId() {
		return facilityTypeFacilityId;
	}
	public void setFacilityTypeFacilityId(Long facilityTypeFacilityId) {
		this.facilityTypeFacilityId = facilityTypeFacilityId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_type_id")  
	public FacilityType getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id")
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}