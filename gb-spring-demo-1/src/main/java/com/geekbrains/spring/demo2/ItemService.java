package com.geekbrains.spring.demo2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemService {
    private ItemRepository itemRepository;

    @Value("Hello")
    private String str1;

    @Value("100")
    int val1;

    public ItemService(@Qualifier(value = "inMemoryItemRepository") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        return itemRepository.findItemById(id);
    }
}
