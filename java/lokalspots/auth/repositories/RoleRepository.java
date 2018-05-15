package lokalspots.auth.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import lokalspots.auth.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Set<Role> findByName(String string);
	
}