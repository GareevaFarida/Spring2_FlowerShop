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
import ru.geekbrains.persist.model.Color;
import ru.geekbrains.persist.repo.ColorRepository;

@Controller
public class ColorController {

    private static final Logger logger = LoggerFactory.getLogger(ColorController.class);

    private final ColorRepository colorRepository;

    @Autowired
    public ColorController(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @GetMapping("/colors")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Colors");
        model.addAttribute("colors", colorRepository.findAll());
        return "colors";
    }

    @GetMapping("/color/create")
    public String adminColorCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Colors");
        model.addAttribute("color", new Color());
        return "color_form";
    }

    @GetMapping("/color/{id}/edit")
    public String adminEditColor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Colors");
        model.addAttribute("category", colorRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "color_form";
    }

    @GetMapping("/color/{id}/delete")
    public String adminDeleteColor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Colors");
        colorRepository.deleteById(id);
        return "redirect:/colors";
    }

    @PostMapping("/color")
    public String adminUpsertColor(Model model, RedirectAttributes redirectAttributes, Color color) {
        model.addAttribute("activePage", "Colors");

        try {
            colorRepository.save(color);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating color", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (color.getId() == null) {
                return "redirect:/color/create";
            }
            return "redirect:/color/" + color.getId() + "/edit";
        }
        return "redirect:/colors";
    }
}

