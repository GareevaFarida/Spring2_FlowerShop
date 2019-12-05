package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Flower;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import sun.util.resources.cldr.te.CalendarData_te_IN;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepr {

    private Long id;

    private String name;

    private BigDecimal price;

    private String categoryName;

    private Long categoryId;

    private String brandName;

    private List<Long> pictureIds;

    private List<String> flowerNames;

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        Category category = product.getCategory();
        this.categoryName = category.getName();
        this.categoryId = category.getId();
        this.brandName = product.getBrand().getName();
        this.pictureIds = product.getPictures().stream()
                .map(Picture::getId)
                .collect(Collectors.toList());
        this.flowerNames = product.getFlowers().stream()
                .map(Flower::getName)
                .collect(Collectors.toList());
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getFlowerNames() {
        return flowerNames;
    }

    public void setFlowerNames(List<String> flowerNames) {
        this.flowerNames = flowerNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(List<Long> pictureIds) {
        this.pictureIds = pictureIds;
    }
}
