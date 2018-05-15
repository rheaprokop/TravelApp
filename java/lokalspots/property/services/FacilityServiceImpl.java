package lokalspots.property.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lokalspots.property.models.Facility;
import lokalspots.property.models.FacilityService;
import lokalspots.property.repositories.FacilityRepository;

@Service
@Primary
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	FacilityRepository facilityRepo;  
	
	@Override
	public Facility findById(Long id) {
	return this.facilityRepo.findOne(id);
	}
	
	@Override
	public List<Facility> findAll() {
		return this.facilityRepo.findAll();
	}
}
