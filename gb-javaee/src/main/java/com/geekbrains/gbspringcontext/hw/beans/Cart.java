package com.geekbrains.gbspringcontext.hw.beans;

import com.geekbrains.gbjavaee.models.Product;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Cart {
    private static long id_sequence = 1L;
    List<Product> cart;

    @Getter
    private final long id;

    ProductRepository repository;


    @Autowired
    public Cart(List<Product> cart, ProductRepository repository) {
        this.cart = cart;
        this.id = id_sequence++;
        this.repository = repository;
    }


    public void addProduct(int id) {
        cart.add(repository.getProduct(id));
    }

    public void deleteProduct(int id) {
        cart.remove(repository.getProduct(id));
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    @PostConstruct
    public void init() {
        System.out.println("Cart #" + id + " is created");
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Product p :cart) {
            s.append(p);
            s.append("\n")
;        }
        return s.toString();
    }
}
