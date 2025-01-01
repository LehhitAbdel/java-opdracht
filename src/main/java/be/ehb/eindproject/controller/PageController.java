package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.ToestelRepo;
import be.ehb.eindproject.model.Toestellen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class PageController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Looks for register.html in src/main/resources/templates
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Looks for login.html in src/main/resources/templates
    }

    private final ToestelRepo toestelRepo;

    @Autowired
    public PageController(ToestelRepo toestelRepo) {
        this.toestelRepo = toestelRepo;
    }

    @GetMapping("/inventory")
    public String showInventoryPage(@RequestParam(required = false) String category, Model model) {
        List<Toestellen> toestellenList;

        if (category != null && !category.isEmpty()) {
            toestellenList = toestelRepo.findAllByCategory(category); // Fetch by category
        } else {
            toestellenList = (List<Toestellen>) toestelRepo.findAll(); // Fetch all
        }

        model.addAttribute("toestellen", toestellenList);
        model.addAttribute("selectedCategory", category);
        return "inventory";
    }


}
