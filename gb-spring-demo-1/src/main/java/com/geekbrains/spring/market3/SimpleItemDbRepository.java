package com.geekbrains.spring.market3;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleItemDbRepository implements SimpleItemRepository {
    @Override
    public List<SimpleItem> getAllItems() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addItem(SimpleItem item) {
        throw new UnsupportedOperationException();
    }
}
