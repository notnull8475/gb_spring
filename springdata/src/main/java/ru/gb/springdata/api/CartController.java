package ru.gb.springdata.api;


import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dto.CartProductDto;
import ru.gb.springdata.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    public List<CartProductDto> cartItems() {
        return service.getAll();
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam Long id) {
        service.addProduct(id);
    }

    @GetMapping("/change")
    public void changeCountOfProduct(@RequestParam Long productId, @RequestParam int delta){
        service.changeCount(productId,delta);
    }

    @DeleteMapping("/remove")
    public void removeProductFromCart(@RequestParam Long productId) {
        service.removeProduct(productId);
    }

}
