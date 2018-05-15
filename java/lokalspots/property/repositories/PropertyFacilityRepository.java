package lokalspots.property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lokalspots.property.models.PropertyFacility;

@Repository
public interface PropertyFacilityRepository extends JpaRepository<PropertyFacility, Long> {

}
