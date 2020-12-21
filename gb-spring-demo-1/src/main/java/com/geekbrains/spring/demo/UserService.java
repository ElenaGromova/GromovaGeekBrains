package com.geekbrains.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setInMemoryUserRepository(@Qualifier(value = "inMemoryUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void printAllUsers() {
        List<User> users = userRepository.findAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }
}