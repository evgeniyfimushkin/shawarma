package edu.evgen.shawarma.security;

import edu.evgen.shawarma.data.UserRepository;
import edu.evgen.shawarma.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepo
    ) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User `" + username + "` not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomOAuth2UserService customOAuth2UserService
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .headers(
                        headers -> headers
                                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
                )
                .authorizeHttpRequests(authorizeHttpRequests ->
                                authorizeHttpRequests
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                                        .requestMatchers("/h2-console/**").hasRole("USER")
                                        .requestMatchers("/design", "/order").hasRole("USER")
//                                .requestMatchers("/design", "/order").rememberMe()
                                        .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login")
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint
                                                .userService(customOAuth2UserService)
                                                .userAuthoritiesMapper(authorities -> {
                                                    Set<GrantedAuthority> mappedAuthorities =
                                                            new HashSet<>(authorities);
                                                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                                                    return mappedAuthorities;
                                                })))
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/")
                )
                .rememberMe(Customizer.withDefaults());
        return http.build();

    }

}
