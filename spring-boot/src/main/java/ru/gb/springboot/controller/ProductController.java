package ru.gb.springboot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/addproduct")
    public void create(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/change_price")
    public void changePrice(@RequestParam int productId, @RequestParam String delta) {
        System.out.println("id " + productId + " delta " + delta);
        productService.changePrice(productId, Integer.parseInt(delta));
    }
}
