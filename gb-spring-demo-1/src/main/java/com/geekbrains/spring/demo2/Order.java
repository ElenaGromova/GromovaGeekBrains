package com.geekbrains.spring.demo2;

import java.util.List;

public class Order {
    private Long id;
    private List<Item> items;
    private User user;

    public Order(Long id, List<Item> items, User user) {
        this.id = id;
        this.items = items;
        this.user = user;
    }
}
