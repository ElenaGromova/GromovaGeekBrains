package com.geekbrains.geek.market.entities;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@Component
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
