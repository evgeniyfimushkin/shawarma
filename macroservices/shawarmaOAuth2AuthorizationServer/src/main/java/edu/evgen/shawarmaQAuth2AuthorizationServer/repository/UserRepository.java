package edu.evgen.shawarmaQAuth2AuthorizationServer.repository;

import edu.evgen.shawarmaQAuth2AuthorizationServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
