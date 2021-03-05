package securityPack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import securityPack.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
