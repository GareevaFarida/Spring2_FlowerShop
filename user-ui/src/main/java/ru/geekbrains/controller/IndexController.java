package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.service.CategoryService;

@Controller
public class IndexController {

    private CategoryService categoryService;

    @Autowired
    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "index")
    public String mainPage(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "index";
    }

    @GetMapping(value = "about")
    public String aboutPage(){
        return "about";
    }

    @GetMapping(value = "/cart")
    public String cartPage(){
        return "cart";
    }

    @GetMapping(value = "specials")
    public String specialsPage(){
        return "specials";
    }

    @GetMapping(value = "myaccount")
    public String myaccountPage(){
        return "myaccount";
    }

    @GetMapping(value = "register")
    public String registertPage(){
        return "register";
    }

    @GetMapping(value = "product")
    public String detailsPage(){
        return "details";
    }

    @GetMapping(value = "contact")
    public String contactPage(){
        return "contact";
    }
 }
