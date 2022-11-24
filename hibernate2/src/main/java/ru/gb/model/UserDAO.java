package ru.gb.model;

import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    boolean deleteUser(User user);

    List<Product> getProducts(User user);


}
