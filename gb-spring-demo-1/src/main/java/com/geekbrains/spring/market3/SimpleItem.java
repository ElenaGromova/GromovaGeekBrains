package com.geekbrains.spring.market3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimpleItem {
    private Long id;
    private String title;

    @PostConstruct
    public void init() {
        id = 1L;
        title = "Box";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("id: %d title: %s", id, title);
    }

    public SimpleItem() {
    }

    public SimpleItem(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
