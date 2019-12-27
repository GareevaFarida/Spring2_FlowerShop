package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImpleTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    private void init(){
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void findCategoryNameByIdTest(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Category1");
        Optional<Category> optionalCategory = Optional.of(category);
        when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
        assertEquals("All Categories",categoryService.findCategoryNameById(-1L));
        assertEquals("NOT FOUND ID = 777",categoryService.findCategoryNameById(777L));
        assertEquals("Category2",categoryService.findCategoryNameById(1L));
    }

    private String getCategoryNameByIdFromMock(long category_id) {
        return null;
    }

}
