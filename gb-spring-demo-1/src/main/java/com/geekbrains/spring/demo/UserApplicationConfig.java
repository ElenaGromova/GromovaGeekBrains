package com.geekbrains.spring.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.geekbrains.spring.demo"})
public class UserApplicationConfig {
    @Bean
    public Square square() {
        Square out = new Square(10);
        out.setArea(50);
        return out;
    }
}
