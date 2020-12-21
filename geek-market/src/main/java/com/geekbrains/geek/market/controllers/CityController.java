package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.City;
import com.geekbrains.geek.market.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping(produces = "application/json")
    public List<City> getAllCities() {
        return cityService.findAll();
    }
}
