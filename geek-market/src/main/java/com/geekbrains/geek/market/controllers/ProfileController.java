package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PutMapping
    public void updateProfile(@RequestBody Profile profile){
        System.out.println("id "+ profile.getId() + " profile " + profile);
        Profile p = profileService.findById(profile.getId()).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + profile.getId()));;
        p.setFirstname(profile.getLastname());
        p.setLastname(profile.getFirstname());
        p.setPhone(profile.getPhone());
        p.setEmail(profile.getEmail());
        p.setBirthday(profile.getBirthday());
        p.setGender(profile.getGender());
        p.setCity(profile.getCity());
    }

}
