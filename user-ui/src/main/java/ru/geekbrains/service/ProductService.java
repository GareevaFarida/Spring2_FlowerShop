package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> getAllProducts();

    ProductRepr getProductById(Long id);

    List<ProductRepr> getProductsByCategoryId(Long id);

    Optional<ProductRepr> findById(Long id);
}
