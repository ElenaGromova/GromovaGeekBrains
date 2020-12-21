package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.City;
import com.geekbrains.geek.market.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
