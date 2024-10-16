package edu.evgen.shawarma.security;

import edu.evgen.shawarma.data.UserRepository;
import edu.evgen.shawarma.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User `" + username + "` not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf->csrf.disable())
                .headers(
                        headers->headers
                        .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
                )
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/h2-console/**").hasRole("USER")
                                .requestMatchers("/design", "/order").hasRole("USER")
                                .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(Customizer.withDefaults());
        return http.build();
//
//        return http
//                .authorizeHttpRequests()
//                .requestMatchers("/design","/order").hasRole("USER")
//                .requestMatchers("/","/**").permitAll()
//                .and().build();

    }
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable() // Отключите CSRF для H2 Console
//                .authorizeRequests()
//                .requestMatchers("/h2-console/**").permitAll() // Разрешите доступ к H2 Console
//                .anyRequest().authenticated() // Для остальных URL требуется аутентификация
//                .and()
//                .headers().frameOptions().sameOrigin().and().build(); // Разрешите фреймы для H2 Console
//    }
}
