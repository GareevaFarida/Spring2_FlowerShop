package ru.geekbrains.service;

import ru.geekbrains.persist.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryId(Long id);

}
