package com.geekbrains.spring.market3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {
    private UserService userService;
    private SimpleItemService simpleItemService;
    private OrderRepository orderRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSimpleItemService(SimpleItemService simpleItemService) {
        this.simpleItemService = simpleItemService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createNewOrder(String username, Long simpleItemId) {
        User user = userService.findByName(username);
        SimpleItem simpleItem = simpleItemService.findById(simpleItemId);
        Order order = new Order(user, 100);
        order.addItem(simpleItem);
        return orderRepository.save(order);
    }
}
