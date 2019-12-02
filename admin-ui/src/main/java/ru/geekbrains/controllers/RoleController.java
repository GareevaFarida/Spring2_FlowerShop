package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controllers.repr.UserRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.repo.RoleRepository;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;

@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }

    @GetMapping("/role/create")
    public String adminBrandCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @GetMapping("/role/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "role_form";
    }

    @GetMapping("/role/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }

    @PostMapping("/role")
    public String adminUpsertBrand(Model model, RedirectAttributes redirectAttributes, Role role) {
        model.addAttribute("activePage", "Roles");

        try {
            roleRepository.save(role);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating role", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (role.getId() == null) {
                return "redirect:/role/create";
            }
            return "redirect:/role/" + role.getId() + "/edit";
        }
        return "redirect:/roles";
    }
}
