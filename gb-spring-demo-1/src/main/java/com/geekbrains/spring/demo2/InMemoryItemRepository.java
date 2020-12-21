package com.geekbrains.spring.demo2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryItemRepository implements ItemRepository {
    private List<Item> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>(Arrays.asList(
                new Item(1L, "Bread", 25),
                new Item(2L, "Milk", 90),
                new Item(3L, "Cheese", 525)
        ));
    }

    public List<Item> findAll() {
        return Collections.unmodifiableList(items);
    }

    public Item findItemById(Long id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("Item not found");
    }

    public void save(Item item) {
        items.add(item);
    }
}
