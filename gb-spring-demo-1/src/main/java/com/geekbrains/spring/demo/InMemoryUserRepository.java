package com.geekbrains.spring.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryUserRepository implements UserRepository {
    private List<User> users;

    @PostConstruct
    public void init() {
        this.users = new ArrayList<>();
        this.users.add(new User(1L, "Bob", "bob@gmail.com"));
        this.users.add(new User(2L, "John", "john@gmail.com"));
    }

    public List<User> findAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(User user) {
        users.add(user);
    }
}
