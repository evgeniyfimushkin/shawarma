package edu.evgen.shawarma_admin.security;

import edu.evgen.shawarma_admin.service.IngredientService;
import edu.evgen.shawarma_admin.service.RestIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

import static edu.evgen.shawarma_admin.Colors.GREEN;
import static edu.evgen.shawarma_admin.Colors.RESET;

@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                  authorizeHttpRequests.anyRequest().permitAll()
//                                authorizeHttpRequests.anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2Login ->
                                oauth2Login
                                        .loginPage("/oauth2/authorization/admin")
                )
                .oauth2Client(Customizer.withDefaults())
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("admin")
                .clientId("admin")
                .clientSecret("admin")
                .scope("writeIngredients", "deleteIngredients", "openid")
                .authorizationUri("http://localhost:9000/oauth2/authorize")
                .tokenUri("http://localhost:9000/oauth2/token")
                .userInfoUri("http://localhost:9000/userinfo")
                .redirectUri("http://localhost:9090/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
        log.info(GREEN + clientRegistration.toString() + RESET);
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    @Bean
    @RequestScope
    public IngredientService ingredientService(
            OAuth2AuthorizedClientService clientService
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            if(clientRegistrationId.equals("admin")){
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                        clientRegistrationId,oauthToken.getName()
                );
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        return new RestIngredientService(accessToken);
    }

}
