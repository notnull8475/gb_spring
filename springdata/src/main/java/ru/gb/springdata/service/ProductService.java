package ru.gb.springdata.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void changePrice(int productId, int delta) {
        Product p = getProduct(productId);
        p.setPrice(p.getPrice() + delta);
    }

    public List<Product> getProductListOfRows(int rowsNumber, int page) {
        return repository.findAll(PageRequest.of(page,rowsNumber)).getContent();
    }
}
