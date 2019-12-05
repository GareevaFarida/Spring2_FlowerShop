package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void insertListCategoriesInModel(Model model) {
        model.addAttribute("categories", getAllCategory());
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public String findCategoryNameById(Long id) {
        if (id == -1) {
            return "All Categories";
        }
        try {
            return findCategoryById(id).getName();
        } catch (IllegalStateException ex) {
            return "NOT FOUND ID = " + id;
        }
    }
}
