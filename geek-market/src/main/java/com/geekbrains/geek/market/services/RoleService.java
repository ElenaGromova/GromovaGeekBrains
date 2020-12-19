package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
	
	public Role getUserRole(){
		return roleRepository.findByName("ROLE_USER");
	}
}
