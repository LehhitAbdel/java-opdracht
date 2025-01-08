package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.UserRepo;
import be.ehb.eindproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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


    @PostMapping("/login")
    public RedirectView loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<Users> user = userRepo.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return new RedirectView("/inventory");
        }

        return new RedirectView("/login?error=true");
    }
}
