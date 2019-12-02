package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.persist.model.Flower;
import ru.geekbrains.persist.repo.FlowerRepository;

@Controller
public class FlowerController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @GetMapping("/flowers")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Flowers");
        model.addAttribute("flowers", flowerRepository.findAll());
        return "flowers";
    }

    @GetMapping("/flower/create")
    public String adminBrandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Flowers");
        model.addAttribute("flower", new Flower());
        return "flower_form";
    }

    @GetMapping("/flower/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Flowers");
        model.addAttribute("flower", flowerRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "flower_form";
    }

    @GetMapping("/flower/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Flowers");
        flowerRepository.deleteById(id);
        return "redirect:/flowers";
    }

    @PostMapping("/flower")
    public String adminUpsertBrand(Model model, RedirectAttributes redirectAttributes, Flower flower) {
        model.addAttribute("activePage", "Flowers");

        try {
            flowerRepository.save(flower);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating flower", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (flower.getId() == null) {
                return "redirect:/flower/create";
            }
            return "redirect:/flower/" + flower.getId() + "/edit";
        }
        return "redirect:/flowers";
    }
}
