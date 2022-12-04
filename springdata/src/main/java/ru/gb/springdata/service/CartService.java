package ru.gb.springdata.service;

import org.springframework.stereotype.Service;
import ru.gb.springdata.bean.Cart;
import ru.gb.springdata.dto.CartProductDto;

import java.util.List;

@Service
public class CartService {

    private final Cart cart;
    private final ProductService productService;

    public CartService(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    public List<CartProductDto> getAll() {
        return cart.getUserProductList();
    }

    public void addProduct(Long id) {
        if (cart.getCardProductDtoById(id) != null) {
            cart.getCardProductDtoById(id).changeCount(1);
        } else cart.getUserProductList().add(new CartProductDto(productService.getProduct(id)));
    }

    public void changeCount(Long id, int delta) {
        cart.getCardProductDtoById(id).changeCount(delta);
    }

    public void removeProduct(Long id) {
        cart.getUserProductList().remove(cart.getCardProductDtoById(id));
    }
}
