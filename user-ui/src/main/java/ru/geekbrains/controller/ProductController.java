package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

@Controller
public class ProductController {

    ProductService productService;
    CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/product_details/{id}")
    public String productDetailPage(@PathVariable("id") Long id, Model model) {
        categoryService.insertListCategoriesInModel(model);
        model.addAttribute("product", productService.getProductById(id));
        return "product_details";
    }
}
