package com.geekbrains.spring.market3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleItemService {
    private SimpleItemRepository simpleItemRepository;

    @Autowired
    public void setSimpleItemRepository(@Qualifier(value = "simpleItemDbRepository") SimpleItemRepository  simpleItemRepository) {
        this.simpleItemRepository = simpleItemRepository;
    }

    public List<SimpleItem> getAllItems() {
        return simpleItemRepository.getAllItems();
    }

    public SimpleItem findById(Long id) {
        throw new UnsupportedOperationException();
    }
}
