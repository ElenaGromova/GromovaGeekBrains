package com.geekbrains.spring.market3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SimpleItemInMemoryRepository implements SimpleItemRepository {
    private List<SimpleItem> items;

    @PostConstruct
    public void initialData() {
        items = new ArrayList<>();
        items.add(new SimpleItem(1L, "Box"));
        items.add(new SimpleItem(2L, "Table"));
        items.add(new SimpleItem(3L, "Screen"));
    }

    @Override
    public List<SimpleItem> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void addItem(SimpleItem item) {
        items.add(item);
    }
}
