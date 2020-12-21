package com.geekbrains.spring.demo2;

import java.util.Collections;
import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
    Item findItemById(Long id);
    void save(Item item);
}
