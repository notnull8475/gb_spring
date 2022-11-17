package com.geekbrains.gbspringcontext.lesson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StringContainer {

//  @Autowired
//  @Qualifier("firstStringBean")

    @Autowired
    private String second;

//  public StringContainer(@Qualifier("firstStringBean") String bean) {
//    this.bean = bean;
//  }

    @PostConstruct
    public void init() {
        System.out.println("injected: " + second);
    }

}
