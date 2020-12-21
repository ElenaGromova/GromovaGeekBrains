package com.geekbrains.geek.market.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "genders")
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
