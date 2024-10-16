package edu.evgen.shawarma.security;

import edu.evgen.shawarma.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String phone;
    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password),fullname,street,phone);
    }
}
