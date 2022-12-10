package ru.gb.springsecuritymvc.dto;

import lombok.Data;
import ru.gb.springsecuritymvc.model.Product;

@Data
public class CartProductDto {
    private Long productId;
    private String title;
    private Long price;
    private Integer count;
    private Long sumPrice;

    public void changeCount(int count) {
        this.count += count;
        sumPrice = price * this.count;
    }

    public CartProductDto(Long productId, String title, Long price) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.count = 1;
        this.sumPrice = price;
    }

    public CartProductDto(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.count = 1;
        this.sumPrice = product.getPrice();
    }
}
