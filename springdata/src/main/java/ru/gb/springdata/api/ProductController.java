package ru.gb.springdata.api;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.service.ProductService;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "rows", required = false, defaultValue = "5") int rows,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "min_price", required = false) Long minPrice,
            @RequestParam(name = "max_price", required = false) Long maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (rows<0) rows = 5;
        if (page<0) page = 1;
        return productService.getProducts(rows,page,minPrice,maxPrice,titlePart);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(productService.getProduct(id));
    }

    @PostMapping
    public void create(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }
    @PutMapping
    public void update(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
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
