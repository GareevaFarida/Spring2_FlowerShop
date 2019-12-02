package ru.geekbrains.service;

import org.springframework.ui.Model;
import ru.geekbrains.persist.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();
    void insertListCategoriesInModel(Model model);
}