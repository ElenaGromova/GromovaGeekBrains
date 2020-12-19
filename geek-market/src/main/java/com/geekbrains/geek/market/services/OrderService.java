package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
