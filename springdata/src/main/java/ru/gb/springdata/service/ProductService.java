package ru.gb.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;
import ru.gb.springdata.repository.specifications.ProductSpecifications;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public Page<ProductDto> getProducts(int rows, int page, Long minPrice, Long maxPrice, String titlePart) {
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null)
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        if (maxPrice != null)
            specification = specification.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        if (titlePart != null) specification = specification.and(ProductSpecifications.titleLike(titlePart));

        return repository.findAll(specification, PageRequest.of(page, rows)).map(ProductDto::new);
    }

    public Product getProduct(long id) {
        return repository.findById(id).get();
    }

    public Product save(ProductDto productDto) {
        return repository.save(new Product(productDto));
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product;
        product = repository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить, продукт не найден"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return product;
    }

    public void deleteProduct(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void changePrice(long productId, int delta) {
        Product p = repository.findById(productId).get();
        p.setPrice(p.getPrice() + delta);
    }
}
