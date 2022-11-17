package com.geekbrains.gbspringcontext.hw.beans;

import com.geekbrains.Utils;
import com.geekbrains.gbjavaee.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductRepository {

    private final List<Product> products;

    @Autowired
    public ProductRepository() {
        products = Utils.generateProducts(5);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void save(Product product) {
        products.add(product);
    }
}
