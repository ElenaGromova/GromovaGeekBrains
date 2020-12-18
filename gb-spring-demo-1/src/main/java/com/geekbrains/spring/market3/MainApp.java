package com.geekbrains.spring.market3;

import com.geekbrains.spring.market2.Market2Config;
import com.geekbrains.spring.market2.Product;
import com.geekbrains.spring.market2.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Market3Config.class);

//        SimpleItemService simpleItemService = context.getBean("simpleItemService", SimpleItemService.class);
//        System.out.println(simpleItemService.getAllItems());

        System.out.println(context.getBean("demoBean"));

        context.close();
    }
}
