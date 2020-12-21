package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Gender;
import com.geekbrains.geek.market.repositories.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderService {
    private final GenderRepository genderRepository;

    public List<Gender> findAll() {
        return genderRepository.findAll();
    }
}
