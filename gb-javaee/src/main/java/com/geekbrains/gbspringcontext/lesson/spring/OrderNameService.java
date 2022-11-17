package com.geekbrains.gbspringcontext.lesson.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderNameService {

  private static long orderSequence = 1L;

  @PostConstruct
  public void init() {
    System.out.println("Создали бин класса OrderNameService");
  }

  public void initName(Order order) {
    order.setName("Заказ #" + orderSequence++);
  }

}
