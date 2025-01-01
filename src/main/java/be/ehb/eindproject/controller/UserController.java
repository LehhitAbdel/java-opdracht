package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.UserRepo;
import be.ehb.eindproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

//requests for users table

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("")
    public HttpStatus createUser(@RequestParam("user") String name, @RequestParam("password") String password, @RequestParam("email") String email) {

        Users newUser = new Users();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setEmail(email);
        userRepo.save(newUser);
        return HttpStatus.OK;


    }
}
