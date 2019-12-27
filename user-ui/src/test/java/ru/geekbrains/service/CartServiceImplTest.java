package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceImplTest {
    private CartService cartService;

    @BeforeEach
    public void createCart() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void removeProductTest() {
        addProductInCart("Brand", 1L, 1L, "Product1", 10, 5);
        addProductInCart("Brand", 1L, 2L, "Product2", 10, 9);
        addProductInCart("Brand", 1L, 3L, "Product3", 10, 3);
        ProductRepr deletingProduct = new ProductRepr();
        deletingProduct.setId(2L);
        cartService.removeProduct(deletingProduct);
        List<LineItem> lineItemList = cartService.getLineItems();
        assertNotNull(lineItemList);
        assertEquals(2, lineItemList.size());
        Set<LineItem> set = lineItemList.stream()
                .filter(i -> i.getProductId() == 2L)
                .collect(Collectors.toSet());
        assertTrue(set.isEmpty());

    }

    @Test
    void getSubTotalTest() {
        addProductInCart("Brand", 1L, 1L, "Product1", 10, 1);
        addProductInCart("Brand", 1L, 2L, "Product2", 10, 2);
        addProductInCart("Brand", 1L, 3L, "Product3", 10, 3);
        assertEquals(new BigDecimal(60), cartService.getSubTotal());
    }

    private void addProductInCart(String brand,
                                  Long id_category,
                                  Long id_product,
                                  String prodName,
                                  int price,
                                  int qty) {
        ProductRepr prod1 = new ProductRepr();
        prod1.setBrandName(brand);
        prod1.setCategoryId(id_category);
        prod1.setId(id_product);
        prod1.setName(prodName);
        prod1.setPrice(new BigDecimal(price));
        cartService.addProductQty(prod1, qty);
    }
}
