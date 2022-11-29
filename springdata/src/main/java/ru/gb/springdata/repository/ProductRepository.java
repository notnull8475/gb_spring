package ru.gb.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdata.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
