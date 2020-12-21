package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile update(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> findById(Long id) {return profileRepository.findById(id);  }


}
