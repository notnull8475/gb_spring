package ru.gb.springsecuritymvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springsecuritymvc.model.Product;
import ru.gb.springsecuritymvc.dto.ProductDto;
import ru.gb.springsecuritymvc.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
//    @ResponseBody
    public String getProduct(@PathVariable int id, Model model) {
        Product product = service.getProduct(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping
    public String getProducts(@RequestParam(name = "rows", required = false, defaultValue = "5") int rows,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "min_price", required = false) Long minPrice,
                              @RequestParam(name = "max_price", required = false) Long maxPrice,
                              @RequestParam(name = "title_part", required = false) String titlePart,
                              Model model) {
        model.addAttribute("productList", service.getProducts(rows,page,minPrice,maxPrice,titlePart).toList());
        return "products";
    }

    @GetMapping("/addproduct")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/addproduct")
    public String create(ProductDto productDto) {
        service.save(productDto);
        return "/addproduct";
    }
}
