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

    @GetMapping("/list")
    public List<Product> getProductsListOfRows(@RequestParam int rowsNumber, @RequestParam int page){
        return  productService.getProductListOfRows(rowsNumber, page);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/change_price")
    public void changePrice(@RequestParam int productId, @RequestParam String delta) {
        productService.changePrice(productId, Integer.parseInt(delta));
    }
}
