package com.geekbrains.gbspringmvc.controllers;

import com.geekbrains.gbjavaee.models.Product;
import com.geekbrains.gbspringcontext.hw.beans.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ApplicationContext context = new AnnotationConfigApplicationContext("com.geekbrains.gbspringcontext.hw.beans");
    private ProductRepository repository = context.getBean(ProductRepository.class);

    @GetMapping("/{id}")
//    @ResponseBody
    public String getProduct(@PathVariable int id,Model model) {
        Product product = repository.getProduct(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("productList", repository.getProducts());
        return "products";
    }

    @GetMapping("/addproduct")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/addproduct")
    public String create(Product product) {
        repository.save(product);
        return "/addproduct";
    }
}
