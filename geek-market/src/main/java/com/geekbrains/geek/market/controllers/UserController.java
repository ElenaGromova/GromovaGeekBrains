package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/profile")
    public Profile getProfile(Principal principal) {
        return (userService.findByUsername(principal.getName())).get().getProfile();
    }

    @PostMapping("/confirmPassword")
    public Boolean checkPassword(Principal principal, @RequestParam String password){
        return passwordEncoder.matches(password, ((userService.findByUsername(principal.getName())).get().getPassword()));
    }

}
