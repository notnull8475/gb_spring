package com.geekbrains.gbspringcontext.hw;

import com.geekbrains.gbjavaee.models.Product;
import com.geekbrains.gbspringcontext.hw.beans.Cart;
import com.geekbrains.gbspringcontext.hw.beans.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    /*Задание
     * 1. Есть класс Product (id, название, цена).
     * Товары хранятся в бине ProductRepository,
     * в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
     * 2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять и удалять товары по id.
     * 3. Написать консольное приложение, позволяющее управлять корзиной.
     * 4. При каждом запросе корзины из контекста, должна создаваться новая корзина.
     * Дополнительные материалы
     * Singleton и Prototype.
     * Beans и Factory method.
     * Крис Шефер, Кларенс Хо, Роб Харроп. Spring 4 для профессионалов.
     * */

    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext("com.geekbrains.gbspringcontext.hw.beans");
        ProductRepository repository = context.getBean(ProductRepository.class);
        Scanner scanner = new Scanner(System.in);
        String help = " 0 - выйти" +
                " 1 - взять корзину " +
                " 2 - Показать продукты для покупки" +
                " 3 - Показать продукты в корзине ";
        System.out.println(help);
        Cart cart = null;
        boolean work = true;
        while (work) {
            int choise = scanner.nextInt();
            if (choise == 0) {
                work = false;
            } else {
                if (choise == 1) {
                    cart = context.getBean(Cart.class);
                    System.out.println("Взята новая корзина");
                }
                if (1 < choise) {
                    if (cart == null) {
                        System.out.println(help);
                    } else {
                        if (choise == 2) {
                            int i = 1;
                            for (Product p : repository.getProducts()) {
                                System.out.println(i++ + p.toString());
                            }
                            System.out.println("Выберите продукт для покупки");
                            int productId = scanner.nextInt();
                            if (--productId < repository.getProducts().size()) {
                                cart.addProduct(productId);
                                System.out.println("в корзину добавлен продукт " + repository.getProduct(productId));
                            } else {
                                System.out.println("Нет такого продукта");
                            }
                            System.out.println(help);
                        }
                        if (choise == 3) {
                            if (cart.isEmpty()) {
                                System.out.println("Корзина пуста");
                                System.out.println(help);
                            } else {
                                System.out.println(cart);
                            }
                        }
                    }
                }
            }
        }

    }
}

