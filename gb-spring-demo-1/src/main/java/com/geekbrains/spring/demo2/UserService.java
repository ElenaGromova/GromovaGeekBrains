package com.geekbrains.spring.demo2;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public User getCurrentUser() {
        return new User(1L, "Bob");
    }
}
