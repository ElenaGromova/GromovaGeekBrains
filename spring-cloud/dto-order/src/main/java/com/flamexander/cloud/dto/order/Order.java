package com.flamexander.cloud.dto.order;

import com.flamexander.cloud.dto.common.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "products")
    private List<ProductDto> products;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDto product;

    public Order(List<ProductDto> products) {
        this.products = products;
    }
}
