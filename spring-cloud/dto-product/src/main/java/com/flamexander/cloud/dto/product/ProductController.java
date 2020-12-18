package com.flamexander.cloud.dto.product;

import com.flamexander.cloud.dto.common.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private RestTemplate restTemplate;
    private ProductService productService;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/{List<id>}")
    public List<Product> getProducts(@PathVariable List<Long> ids) {
        return productService.findAllById(ids);
    }

    @GetMapping("/allOrders")
    public List<OrderDto> showOrders() {
        ResponseEntity restExchange = restTemplate.exchange("http://localhost:5555/api/v1/orders/allOrders", HttpMethod.GET, null, List.class);
        return (List<OrderDto>) restExchange.getBody();
    }

}
