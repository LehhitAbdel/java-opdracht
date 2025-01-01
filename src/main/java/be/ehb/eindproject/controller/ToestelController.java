package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.ToestelRepo;
import be.ehb.eindproject.model.Toestellen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toestel")
public class ToestelController {

//requests for toestellen table

    private final ToestelRepo toestelRepo;

    //dependency injection
    @Autowired
    public ToestelController(ToestelRepo toestelRepo) {
        this.toestelRepo = toestelRepo;
    }

    @RequestMapping("")
    public Iterable<Toestellen> findAllToestellen(){
        return toestelRepo.findAll();
    }



}