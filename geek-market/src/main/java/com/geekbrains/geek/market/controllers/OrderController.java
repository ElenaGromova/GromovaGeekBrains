package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.configs.JwtTokenUtil;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final JwtTokenUtil jwtTokenUtil;
    private final Cart cart;

    @GetMapping
    public List<Order> showOrders(Principal principal) {
        System.out.println("(userService.findByUsername(principal.getName())).get().getId() " + (userService.findByUsername(principal.getName())).get().getId());
        return orderService.findByUserId(  (userService.findByUsername(principal.getName())).get().getId() );
    }

    @PostMapping
    public void createOrder(Principal principal,
                             @RequestParam String address,
                             @RequestParam String phone) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address, phone);
        order.setId(null);
        orderService.save(order);
    }

}
