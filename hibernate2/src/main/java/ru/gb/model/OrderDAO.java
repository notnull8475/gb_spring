package ru.gb.model;

import java.util.List;

public interface OrderDAO {

    boolean newOrder(User user, List<Product> productList);

    List<Order> getUserOders(User user);
}
