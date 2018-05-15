package lokalspots.property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lokalspots.property.models.PropertyType;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long>  {
	
}
