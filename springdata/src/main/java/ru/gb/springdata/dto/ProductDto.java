package ru.gb.springdata.dto;

import lombok.Data;
import ru.gb.springdata.model.Product;

@Data
public class ProductDto {
    Long id;
    String title;
    Long price;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}

