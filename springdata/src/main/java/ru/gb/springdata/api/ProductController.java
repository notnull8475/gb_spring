package ru.gb.springdata.api;

import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/app/products")
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
    public Product getProduct(@PathVariable Long id) {
        Product product =  productService.getProduct(id);
        System.out.println(product);
        return product;
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        productService.deleteProduct(id);
    }


}
