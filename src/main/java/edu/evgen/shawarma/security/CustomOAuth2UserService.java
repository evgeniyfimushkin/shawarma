package edu.evgen.shawarma.security;

import edu.evgen.shawarma.data.UserRepository;
import edu.evgen.shawarma.entities.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final DefaultOAuth2UserService deelegate = new DefaultOAuth2UserService();
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = deelegate.loadUser(userRequest);
        String username = oAuth2User.getAttribute("name");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User(
                    username,
                    "",
                    "",
                    "",
                    ""
            );
            user.setEmail(oAuth2User.getAttribute("email"));
            userRepository.save(user);
        }else{
            user.setEmail(oAuth2User.getAttribute("email"));
        }
        return oAuth2User;
    }
}
