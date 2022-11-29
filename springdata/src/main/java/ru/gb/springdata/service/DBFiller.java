package ru.gb.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DBFiller {

    @Autowired
    private ProductRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDBonStart(){
        repository.saveAll(generateProducts(20));
    }


    private static List<Product> generateProducts(int numb) {
        Random r = new Random();
        String[] names = {"Твикс", "Марс", "Сникерс", "Баунти", "Плитки шоколадные", "Конфеты шоколадные", "Карамели", "Карамельные леденцы", "Мармелад", "Вафли", "Пряники", "Пряники к чаю", "Печенье Эсмеральда", "Печенье ювелирное", "Печенье овсяное", "Печенье утреннее", "Печенье Юбилейное", "Кексы", "Мини рулеты", "Рулеты", "Коржи бисквитные", "Сухари Киевские", "Сушки", "Орбит", "Дирол", "Холлс"};
        List<Product> products = new ArrayList<>();
        for (long i = 0; i < numb; i++) {
            Product p = new Product();
            p.setPrice(r.nextLong(1000));
            p.setTitle(names[r.nextInt(names.length)]);
            products.add(p);
        }
        return products;
    }
}
