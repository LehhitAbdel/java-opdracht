package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.Basket;
import be.ehb.eindproject.model.BasketRepo;
import be.ehb.eindproject.model.ToestelRepo;
import be.ehb.eindproject.model.Toestellen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class PageController {

    private final BasketRepo basketRepo;

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
    public PageController(ToestelRepo toestelRepo, BasketRepo basketRepo) {
        this.toestelRepo = toestelRepo;
        this.basketRepo = basketRepo;
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

    @GetMapping("/basket")
    public String showBasketPage(Model model) {
        List<Basket> basketList = (List<Basket>) basketRepo.findAll();
        model.addAttribute("basketItems", basketList);
        return "basket";
    }

    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam("name") String name) {
        Basket basketItem = new Basket(name);
        basketRepo.save(basketItem);
        return "redirect:/basket";
    }



}
