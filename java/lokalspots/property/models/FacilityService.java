package lokalspots.property.models;

import java.util.List;

import org.springframework.data.repository.query.Param;
 

public interface FacilityService { 
	Facility findById(Long id);
	List<Facility> findAll();
	 
}
