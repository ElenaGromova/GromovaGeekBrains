package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Gender;
import com.geekbrains.geek.market.services.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genders")
@RequiredArgsConstructor
public class GenderController {
    private final GenderService genderService;

    @GetMapping(produces = "application/json")
    public List<Gender> getAllCities() {
        return genderService.findAll();
    }
}
