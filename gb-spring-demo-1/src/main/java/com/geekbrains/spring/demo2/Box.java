package com.geekbrains.spring.demo2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Box {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Box() {
    }

    @Override
    public String toString() {
        return "Box: " + size;
    }
}
