package com.geekbrains.spring.market3;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public User findByName(String name) {
        // ...
        return new User();
    }
}
