package ru.gb.spring.ws.services;

import ru.gb.spring.ws.entities.ProductEntity;
import ru.gb.spring.ws.repositories.ProductRepository;
import ru.gb.spring.ws.soap.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = se -> {
        Product s = new Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Product getByTitle(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
