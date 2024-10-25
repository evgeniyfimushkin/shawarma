package edu.evgen.shawarma.security;

import edu.evgen.shawarma.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    @NotBlank(message = "Необходимо ввести логин")
    private String username;
    @NotBlank(message = "Необходимо ввести пароль")
    private String password;
    @NotBlank(message = "Поле подтверждения пароля обязательно")
    private String confirm;
    private String fullname;
    private String street;
    private String phone;
    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password),fullname,street,phone);
    }
}
