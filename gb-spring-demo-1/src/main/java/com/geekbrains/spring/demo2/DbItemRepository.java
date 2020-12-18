package com.geekbrains.spring.demo2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DbItemRepository implements ItemRepository {
    public List<Item> findAll() {
        throw new UnsupportedOperationException("itemRepository.findAll()");
    }

    public Item findItemById(Long id) {
        throw new UnsupportedOperationException("itemRepository.findItemById()");
    }

    public void save(Item item) {
        throw new UnsupportedOperationException("itemRepository.save()");
    }
}
