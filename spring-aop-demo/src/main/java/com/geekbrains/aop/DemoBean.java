package com.geekbrains.aop;

import org.springframework.stereotype.Component;

@Component
public class DemoBean {
    public void doSomethingWithUser(int n, int n2) {
        System.out.println("demo bean method");
    }
}
