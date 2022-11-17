package com.geekbrains.gbspringcontext.lesson;

import com.geekbrains.gbspringcontext.lesson.config.StringContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class JavaConfigMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.gb.config");

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

        // http


//
//    Object first = context.getBean("firstStringBean");
//    System.out.println(first);
//
//    System.out.println(context.getBean("second"));
//
//    String stringBean = context.getBean(String.class);
//    System.out.println(stringBean); // ?

        StringContainer bean = context.getBean(StringContainer.class);


    }
}