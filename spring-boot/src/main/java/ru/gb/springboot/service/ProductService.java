package ru.gb.springboot.service;

import org.springframework.stereotype.Service;
import ru.gb.springboot.model.Product;
import ru.gb.springboot.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.getProducts();
    }

    public void addProduct(Product product){
        productRepository.add(product);
    }

    public void deleteProduct(long id){
        productRepository.delete(id);
    }

    public Product getProduct(long id){
        return productRepository.getProduct(id);
    }

    public void changePrice(long id, int delta) {
        Product product = getProduct(id);
        product.setCost(product.getCost() + delta);
        productRepository.save(id, product);
    }
}
