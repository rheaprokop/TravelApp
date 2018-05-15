package lokalspots.property.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "l0kalsp0ts_PropertyType") 
public class PropertyType {
	private int propertyTypeId;
	private String propertyName;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	} 
	
}
