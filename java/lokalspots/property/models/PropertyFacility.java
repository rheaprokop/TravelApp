package lokalspots.property.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "l0kalsp0ts_Property_Facility")
public class PropertyFacility {
		
	private Long propertyFacilityId;
	private Property property;
	private Facility facility;
	private Date createdDate;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getPropertyFacilityId() {
		return propertyFacilityId;
	}
	public void setPropertyFacilityId(Long propertyFacilityId) {
		this.propertyFacilityId = propertyFacilityId;
	}
	
	@ManyToOne
    @JoinColumn(name = "property_id")  
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
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
