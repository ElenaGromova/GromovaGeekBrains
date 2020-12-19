package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private User user;
    private List<OrderItemDto> items;
    private int price;
    private String address;

    public OrderDto(Order o) {
        this.user = o.getUser();
        this.items = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = o.getPrice();
        this.address = o.getAddress();
    }
}
