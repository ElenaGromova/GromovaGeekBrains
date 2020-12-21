package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping(produces = "application/json")
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }
}
