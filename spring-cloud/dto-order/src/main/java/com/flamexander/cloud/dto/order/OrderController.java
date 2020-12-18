package com.flamexander.cloud.dto.order;

import com.flamexander.cloud.dto.common.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private RestTemplate restTemplate;
    private OrderService orderService;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/allOrders")
    public List<Order> showOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{List<Long>}")
    public Order createOrder(@PathVariable List<Long> products) {
        ResponseEntity restExchange = restTemplate.exchange("http://localhost:5555/cloud/api/v1/product/{List<Long>}", HttpMethod.GET, null, List.class);
        List<ProductDto> dtos = (List<ProductDto>) restExchange.getBody();
        Order order = new Order(dtos);
        return orderService.save(order);
    }

}