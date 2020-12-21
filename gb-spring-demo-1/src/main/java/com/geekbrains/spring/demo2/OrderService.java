package com.geekbrains.spring.demo2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OrderService {
    private UserService userService;
    private ItemService itemService;

    public OrderService(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    public Order createOrderFromItemIdForCurrentUser(Long itemId) {
        User currentUser = userService.getCurrentUser();
        List<Item> items = new ArrayList<>(Arrays.asList(itemService.findItemById(itemId)));
        return new Order(1L, items, currentUser);
    }
}
