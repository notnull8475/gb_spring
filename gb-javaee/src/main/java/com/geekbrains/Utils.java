package com.geekbrains;

import com.geekbrains.gbjavaee.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static List<Product> generateProducts(int numb) {
        Random r = new Random();
        String[] names = {"Твикс", "Марс", "Сникерс", "Баунти", "Плитки шоколадные", "Конфеты шоколадные", "Карамели", "Карамельные леденцы", "Мармелад", "Вафли", "Пряники", "Пряники к чаю", "Печенье Эсмеральда", "Печенье ювелирное", "Печенье овсяное", "Печенье утреннее", "Печенье Юбилейное", "Кексы", "Мини рулеты", "Рулеты", "Коржи бисквитные", "Сухари Киевские", "Сушки", "Орбит", "Дирол", "Холлс"};
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numb; i++) {
            Product p = new Product();
            p.setId(i);
            p.setCost(r.nextDouble(1000));
            p.setTitle(names[r.nextInt(names.length)]);
            products.add(p);
        }
        return products;
    }
}
