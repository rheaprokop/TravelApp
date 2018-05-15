package lokalspots.property.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary; 
import org.springframework.stereotype.Service;

import lokalspots.property.models.PropertyType;
import lokalspots.property.repositories.PropertyTypeRepository;

@Service
@Primary
public class PropertyTypeServiceImpl implements PropertyTypeService {
	@Autowired
	private PropertyTypeRepository propertyTypeRepo;
	
	@Override
	public List<PropertyType> findAll() {
		return this.propertyTypeRepo.findAll();
	}
	 
}
