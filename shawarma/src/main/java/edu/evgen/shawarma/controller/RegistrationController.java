package edu.evgen.shawarma.controller;

import edu.evgen.shawarma.repository.UserRepository;
import edu.evgen.shawarma.entities.User;
import edu.evgen.shawarma.security.RegistrationForm;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(
            @Valid RegistrationForm form,
            BindingResult bindingResult,
            Model model
            ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", form);
            return "registration";
        }
        if (!form.getPassword().equals(form.getConfirm())) {
            model.addAttribute("wrongPassword", true);
            return "registration";
        }
        User user = form.toUser(passwordEncoder);
        if (userRepo.findByUsername(user.getUsername()) == null) {
            userRepo.save(form.toUser(passwordEncoder));
            return "redirect:/login";
        } else {
            model.addAttribute("userExists", true);
            return "registration";
        }
    }

}
