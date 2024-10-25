package edu.evgen.shawarmaQAuth2AuthorizationServer;

import edu.evgen.shawarmaQAuth2AuthorizationServer.entity.User;
import edu.evgen.shawarmaQAuth2AuthorizationServer.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShawarmaQAuth2AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShawarmaQAuth2AuthorizationServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User admin = new User("evgen", passwordEncoder.encode("evgen"), "ROLE_ADMIN");
            admin.setId(1L);
            userRepository.save(admin);

        };
    }
}
