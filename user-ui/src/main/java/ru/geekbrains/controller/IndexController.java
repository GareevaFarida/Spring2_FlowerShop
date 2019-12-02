package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

@Controller
public class IndexController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public IndexController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping(value = "index")
    public String mainPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        model.addAttribute("products",productService.getAllProducts());
        return "index";
    }

    @GetMapping(value = "about")
    public String aboutPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "about";
    }

    @GetMapping(value = "/cart")
    public String cartPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "cart";
    }

    @GetMapping(value = "specials")
    public String specialsPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "specials";
    }

    @GetMapping(value = "myaccount")
    public String myaccountPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "myaccount";
    }

    @GetMapping(value = "register")
    public String registertPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "register";
    }

    @GetMapping(value = "product")
    public String detailsPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "details";
    }

    @GetMapping(value = "contact")
    public String contactPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "contact";
    }

    @GetMapping(value = "details")
    public String productPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "details";
    }
}
