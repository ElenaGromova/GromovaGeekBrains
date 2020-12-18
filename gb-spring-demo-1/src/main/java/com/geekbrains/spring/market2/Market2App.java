package com.geekbrains.spring.market2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Objects;

public class Market2App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Market2Config.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        productService.addProduct(new Product(3L, "Cheese"));
        System.out.println(productService.getAllProducts());

        System.out.println(context.getBean("demoBean"));

        context.close();
    }
}
