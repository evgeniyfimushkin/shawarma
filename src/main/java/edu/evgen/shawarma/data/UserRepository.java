package edu.evgen.shawarma.data;

import edu.evgen.shawarma.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
