package com.flamexander.springsecuritypack.controllers;

import com.flamexander.springsecuritypack.entities.User;
import com.flamexander.springsecuritypack.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
public class DaoContoller {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        // Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }
}
