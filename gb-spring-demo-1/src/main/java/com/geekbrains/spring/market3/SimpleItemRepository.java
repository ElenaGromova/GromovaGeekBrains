package com.geekbrains.spring.market3;

import java.util.Collections;
import java.util.List;

public interface SimpleItemRepository {
    List<SimpleItem> getAllItems();
    void addItem(SimpleItem item);
}
