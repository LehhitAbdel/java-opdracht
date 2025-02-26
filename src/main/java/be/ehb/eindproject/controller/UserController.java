package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.UserRepo;
import be.ehb.eindproject.model.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("")
    public RedirectView createUser(@RequestParam("user") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        Users newUser = new Users();
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        userRepo.save(newUser);

        // Redirect to the login page after successful registration
        return new RedirectView("/login");
    }



}
