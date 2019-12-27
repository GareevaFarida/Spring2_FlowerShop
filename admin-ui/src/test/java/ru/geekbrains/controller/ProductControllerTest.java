package ru.geekbrains.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testProductPostRequest() throws Exception {
        //пыталась протестировать post метод для Product, но ничего не вышло.
        //Для этого меняла аннотацию в методе POST, добавляя consumes = {"application/json","text/html"}
        //                                                  и producers = {"application/json","text/html"}
        //После такого изменения метод POST перестал работать, ошибка 415
        //сначала поместила в H2 новый бренд и категорию, потом создала ProductRepr,
        // заполнила его категорию и бренд, получила json строку.
        //В итоге продукт контроллером так и не создается из-за ошибки либо 415, либо 302.
        //Я правильно поняла, что объекты в h2 создаются только через MockMvc?

        mvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New brand")
                .with(csrf())//для SpringSequrity проверка запуск только от пользователей, которые имеют право
        )

        ;
        Brand brand = new Brand();
        brand.setName("New brand");
        Optional<Brand> savedBrand = brandRepository.findOne(Example.of(brand));

        assertTrue(savedBrand.isPresent());
        System.out.println("Id of New brand = " + savedBrand.get().getId());


        mvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New category")
                .with(csrf())//для SpringSequrity проверка запуск только от пользователей, которые имеют право
        );

        Category category = new Category();
        category.setName("New category");
        Optional<Category> savedCategory = categoryRepository.findOne(Example.of(category));
        assertTrue(categoryRepository.count() > 0);
        System.out.println("Id of New category = " + savedCategory.get().getId());


        ProductRepr productRepr = new ProductRepr();
        productRepr.setName("New Product");
        productRepr.setId(-1L);

        productRepr.setBrand(savedBrand.get());
        productRepr.setCategory(savedCategory.get());

        productRepr.setPrice(new BigDecimal(100));

//        mvc.perform(post("/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(csrf())
//                .content(asJsonString(productRepr)))
//        ;

        mvc.perform(MockMvcRequestBuilders.post("/product")
                .with(csrf())
                .content(asJsonString(productRepr))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        assertTrue(productRepository.count() > 0);


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
