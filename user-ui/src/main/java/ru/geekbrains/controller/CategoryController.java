package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.repo.ProductRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final ProductRepository productRepository;

    @Autowired
    public CategoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "")
    public String categoryPage() {
        return "category";
    }

    @GetMapping(value = "/select")
    public String editForm(@RequestParam("id") Long id, Model model) {
//        Category category = categoryService.findByIdWithProducts(id)
//                .orElseThrow(() -> new IllegalStateException("Category not found"));
//        model.addAttribute("category", category);
//        model.addAttribute("action", "edit");

        model.addAttribute("products",productRepository.findByCategoryId(id));

        return "category";
    }

}