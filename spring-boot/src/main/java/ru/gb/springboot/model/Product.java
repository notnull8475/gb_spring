package ru.gb.springboot.model;

import lombok.Data;

@Data
public class Product {
    long id;
    String name;
    double cost;
}
