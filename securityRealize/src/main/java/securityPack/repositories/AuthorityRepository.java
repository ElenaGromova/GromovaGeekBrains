package securityPack.repositories;

import org.springframework.data.repository.CrudRepository;
import securityPack.entities.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
