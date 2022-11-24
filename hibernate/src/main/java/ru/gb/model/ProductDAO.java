package ru.gb.model;

import java.util.List;

public interface ProductDAO {
    Product findById(long id);

    Product findByTitle(String title);

    List<Product> getAll();

    void save(Product product);

    void saveOrUpdate(Product product);
    void delete(Product product);

    void deleteById(long id);

    void deleteByTitle(String title);

}
