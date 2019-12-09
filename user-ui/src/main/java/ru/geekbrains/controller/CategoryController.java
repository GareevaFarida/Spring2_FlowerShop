package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "")
    public String categoryPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        return "products";
    }

    @GetMapping(value = "/select")
    public String editForm(@RequestParam("id") Long id, Model model) {
        categoryService.insertListCategoriesInModel(model);
        model.addAttribute("products",productService.getProductsByCategoryId(id));
        model.addAttribute("categoryName",categoryService.findCategoryNameById(id));
        return "products";
    }

}