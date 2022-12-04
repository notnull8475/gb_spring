package ru.gb.springdata.validators;

import org.springframework.stereotype.Component;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() < 1) errors.add("Price cant be less than 1");
        if (productDto.getTitle().isBlank()) errors.add("Title cant be blank");
        if (!errors.isEmpty()) throw new ValidationException(errors);
    }
}
