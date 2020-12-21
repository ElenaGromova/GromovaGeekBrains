package com.geekbrains.spring.market2;

public class DemoBean {
    private String title;

    public DemoBean(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DemoBean{" +
                "title='" + title + '\'' +
                '}';
    }
}
