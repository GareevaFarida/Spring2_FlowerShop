package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> getAllProducts() {

        List<ProductRepr> reprList = new ArrayList<>();

        for (Product prod:productRepository.findAll()) {
            reprList.add(new ProductRepr(prod));
        }
        return reprList;
    }

    @Override
    public List<ProductRepr> getProductsByCategoryId(Long id) {
        if (id == -1) {
            return getAllProducts();
        }
        List<ProductRepr> reprList = new ArrayList<>();
        List<Product> products = productRepository.findByCategoryId(id);
        for (Product prod:products) {
            reprList.add(new ProductRepr(prod));
        }
        return reprList;
    }

    @Override
    public ProductRepr getProductById(Long id) {
        //return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Product with id = " + id + " not found."));
        return productRepository.findById(id).map(ProductRepr::new).get();
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }
}
