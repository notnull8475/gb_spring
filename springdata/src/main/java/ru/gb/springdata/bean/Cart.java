package ru.gb.springdata.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.springdata.dto.CartProductDto;

import java.util.List;

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Cart {
    private List<CartProductDto> userProductList;

    @Autowired
    public Cart(List<CartProductDto> cart) {
        this.userProductList = cart;
    }

    public CartProductDto getCardProductDtoById(Long id) {
        return userProductList.stream().filter(cartProductDto -> cartProductDto.getProductId().equals(id)).findFirst().orElse(null);
    }
}
