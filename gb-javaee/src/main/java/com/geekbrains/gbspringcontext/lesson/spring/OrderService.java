package com.geekbrains.gbspringcontext.lesson.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class OrderService {

  // field-injection
//  @Autowired
//  private OrderNameService orderNameService;

  private final OrderNameService orderNameService;

//   constructor-injection
//  @Autowired // необязательно с версии spring 5.*
  public OrderService(OrderNameService orderNameSequence) {
    this.orderNameService = orderNameSequence;
  }

  // setter-injection
//  @Autowired
//  public void setOrderNameService(OrderNameService orderNameService) {
//    this.orderNameService = orderNameService;
//  }

  @PostConstruct
  public void инит2() {
    System.out.println("2 - Создали бин класса OrderService");
  }

  @PostConstruct
  public void инит() {
    System.out.println("1 - Создали бин класса OrderService");
  }

  @PreDestroy
  public void ЫЫ() {
    System.out.println("Удалили бин класса OrderService");
  }

  public Order createNewOrder() {
    Order order = new Order();
    orderNameService.initName(order);
    return order;
  }

}
