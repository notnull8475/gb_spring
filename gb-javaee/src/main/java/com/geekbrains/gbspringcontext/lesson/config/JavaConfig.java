package com.geekbrains.gbspringcontext.lesson.config;

import com.geekbrains.gbspringcontext.lesson.spring.OrderNameService;
import com.geekbrains.gbspringcontext.lesson.spring.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public OrderService orderService(OrderNameService orderNameService) {
        return new OrderService(orderNameService);
    }

    @Bean
    public OrderNameService orderNameService() {
        return new OrderNameService();
    }

    @Bean
    public String firstStringBean() {
        return "I am first string bean";
    }

    @Bean("second")
    public String secondStringBean() {
        return "I am second string bean";
    }

}
