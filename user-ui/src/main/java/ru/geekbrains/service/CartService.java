package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addProductQty(ProductRepr productRepr, int qty);

    void removeProductQty(ProductRepr productRepr, int qty);

    void removeProduct(ProductRepr productRepr);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

    void updateCart(LineItem lineItem);
}
