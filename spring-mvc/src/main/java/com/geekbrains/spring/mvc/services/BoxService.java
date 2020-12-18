package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Box;
import com.geekbrains.spring.mvc.repositories.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class BoxService {
    private BoxRepository boxRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public List<Box> getAllBoxes() {
        return boxRepository.getAllBoxes();
    }

    public void save(Box box) {
        boxRepository.save(box);
    }

    public void deleteById(Long id) {
        boxRepository.deleteById(id);
    }
}
