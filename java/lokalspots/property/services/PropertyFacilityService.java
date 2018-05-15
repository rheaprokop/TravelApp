package lokalspots.property.services;

import java.util.List;
import java.util.Set;

import lokalspots.property.models.PropertyFacility;

public interface PropertyFacilityService {

	void save(Set<PropertyFacility> propertyFacility); 
	
	List<PropertyFacility> findByPropertyId(Long Id);
}
