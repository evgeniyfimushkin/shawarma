package edu.evgen.shawarma.security;

import edu.evgen.shawarma.data.UserRepository;
import edu.evgen.shawarma.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form, Model model) {
        User user = form.toUser(passwordEncoder);
        if (userRepo.findByUsername(user.getUsername()) == null) {
            userRepo.save(form.toUser(passwordEncoder));
            return "redirect:/login";
        }else{
            model.addAttribute("userExists",true);
            return "registration";
        }
    }

}
