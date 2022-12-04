package ru.gb.springdata.model;


import lombok.*;
import ru.gb.springdata.dto.ProductDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "price")
    Long price;

    @Column(name = "create_time")
    Timestamp createTime;

    public Product(ProductDto p) {
        this.setTitle(p.getTitle());
        this.setPrice(p.getPrice());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
    }
}
