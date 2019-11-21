package ru.geekbrains.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/index")
    public String mainPage(){
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
