package com.geekbrains.spring.market3;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private User user;
    private List<SimpleItem> items;
    private int price;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order(User user, int price) {
        this.user = user;
        this.price = price;
        this.items = new ArrayList<>();
    }

    public void addItem(SimpleItem item) {
        items.add(item);
    }
}
