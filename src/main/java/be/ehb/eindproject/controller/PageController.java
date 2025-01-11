package be.ehb.eindproject.controller;

import be.ehb.eindproject.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private void addLoggedInUserToModel(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Users user = (Users) session.getAttribute("user");
            if (user != null) {
                model.addAttribute("username", user.getName());
            }
        }
    }


    @GetMapping("/inventory")
    public String showInventoryPage(@RequestParam(required = false) String category, Model model) {
        // Get the logged-in user's details from the Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        List<Toestellen> toestellenList;
        if (category != null && !category.isEmpty()) {
            toestellenList = toestelRepo.findAllByCategory(category);
        } else {
            toestellenList = (List<Toestellen>) toestelRepo.findAll();
        }

        model.addAttribute("toestellen", toestellenList);
        model.addAttribute("selectedCategory", category);
        return "inventory";
    }



    @GetMapping("/basket")
    public String showBasketPage(Model model) {
        // Get the logged-in user's details from the Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // Get the logged-in user's username
            model.addAttribute("username", username);
        }

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

    @GetMapping("/basket/reserve")
    public String reserveBasketItems() {
        List<Basket> basketList = (List<Basket>) basketRepo.findAll();

        // Mark only unreserved items as reserved
        for (Basket basketItem : basketList) {
            if (!basketItem.isReserved()) {
                basketItem.setReserved(true);
                basketRepo.save(basketItem);
            }
        }

        return "redirect:/basket";
    }



}
