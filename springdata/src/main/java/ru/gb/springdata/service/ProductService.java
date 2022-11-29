package ru.gb.springdata.service;

import org.springframework.stereotype.Service;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(long id) {
        return repository.findById(id).get();
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}
