package com.geekbrains.gbspringcontext.lesson;

import com.geekbrains.gbspringcontext.lesson.spring.Box;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Bean
        // IoC-container Inversion Of Control - инверсия контроля

        // DI - Dependency Injection - внедрение зависимостей

        // configuration (xml) -> bean definitions -> bean factory -> beans -> application context

        // BeanFactory

        AnnotationConfigApplicationContext context =
//      new AnnotationConfigApplicationContext("com.gb.config");
                new AnnotationConfigApplicationContext("com.geekbrains.gbspringcontext.lesson.spring");

        System.out.println(context.getBean(Box.class).getId());
        System.out.println(context.getBean(Box.class).getId());
        System.out.println(context.getBean(Box.class).getId());
        System.out.println(context.getBean(Box.class).getId());
        System.out.println(context.getBean(Box.class).getId());
        System.out.println(context.getBean(Box.class).getId());


//    OrderService orderService = context.getBean(OrderService.class);
//    Order order = orderService.createNewOrder();
//    System.out.println(order);
//
//    System.out.println(orderService.createNewOrder());
//    System.out.println(orderService.createNewOrder());
//    System.out.println(orderService.createNewOrder());
//
//    context.close();

    }
}