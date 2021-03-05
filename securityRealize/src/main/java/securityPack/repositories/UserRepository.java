package securityPack.repositories;

import org.springframework.data.repository.CrudRepository;
import securityPack.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
   // Optional<User> findByUsername(String username);
    User findByUsername(String username);
    List<User> findAll();
}