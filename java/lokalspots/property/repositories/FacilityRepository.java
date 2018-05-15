package lokalspots.property.repositories;
  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lokalspots.property.models.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>  { 
	 
}
