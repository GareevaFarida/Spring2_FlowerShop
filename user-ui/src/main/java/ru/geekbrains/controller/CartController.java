package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.model.LineItem;

@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public CartController(CartService cartService, ProductService productService, CategoryService categoryService) {
        this.cartService = cartService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cartPage(Model model) {
        categoryService.insertListCategoriesInModel(model);
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String updateCart(Model model, LineItem lineItem) {
        categoryService.insertListCategoriesInModel(model);
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.updateCart(lineItem);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/dec", method = RequestMethod.POST)
    public String decrementQty(Model model, LineItem lineItem) {
        categoryService.insertListCategoriesInModel(model);
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.removeProductQty(lineItem.getProductRepr(), 1);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/inc", method = RequestMethod.POST)
    public String incrementQty(Model model, LineItem lineItem) {
        categoryService.insertListCategoriesInModel(model);
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.addProductQty(lineItem.getProductRepr(), 1);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
    public String removeProduct(Model model, LineItem lineItem) {
        categoryService.insertListCategoriesInModel(model);
        lineItem.setProductRepr(productService.findById(lineItem.getProductId())
                .orElseThrow(IllegalArgumentException::new));
        cartService.removeProduct(lineItem.getProductRepr());
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
    public String checkout(Model model) {
        categoryService.insertListCategoriesInModel(model);
        cartService.checkout();
        return "redirect:/cart";
    }
}
