package ru.gb.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.springboot.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductRepository {

    private final List<Product> products;

    @Autowired
    public ProductRepository() {
        products = generateProducts(5);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(long id) {
        return products.stream().filter(it -> Objects.equals(id, it.getId()))
                .findFirst()
                .orElse(null);

    }

    public void add(Product product) {
        products.add(product);
    }

//    public void save(Product product){
//        products.
//    }


    private static List<Product> generateProducts(int numb) {
        Random r = new Random();
        String[] names = {"Твикс", "Марс", "Сникерс", "Баунти", "Плитки шоколадные", "Конфеты шоколадные", "Карамели", "Карамельные леденцы", "Мармелад", "Вафли", "Пряники", "Пряники к чаю", "Печенье Эсмеральда", "Печенье ювелирное", "Печенье овсяное", "Печенье утреннее", "Печенье Юбилейное", "Кексы", "Мини рулеты", "Рулеты", "Коржи бисквитные", "Сухари Киевские", "Сушки", "Орбит", "Дирол", "Холлс"};
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            Product p = new Product();
            p.setId(i);
            p.setCost(roundAvoid(r.nextDouble(1000),3));
            p.setName(names[r.nextInt(names.length)]);
            products.add(p);
        }
        return products;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void delete(long id) {

        products.remove(getProduct(id));
    }

    public void save(long id ,Product product) {
        int i = products.indexOf(getProduct(id));
        products.set(i,product);
    }

}
