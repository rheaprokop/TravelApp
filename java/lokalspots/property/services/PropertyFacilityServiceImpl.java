package lokalspots.property.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lokalspots.property.models.PropertyFacility;
import lokalspots.property.repositories.PropertyFacilityRepository;

@Service
@Primary
public class PropertyFacilityServiceImpl implements PropertyFacilityService{

	@Autowired
	PropertyFacilityRepository propertyFacilityRepo; 
	
	@Override
	public void save(Set<PropertyFacility> propertyFacility) { 
		propertyFacilityRepo.save(propertyFacility);
	} 
	
	@Override
	public List<PropertyFacility> findByPropertyId(Long Id) { 
		return propertyFacilityRepo.findAll();
	}
	
}
