package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.dto.UserDto;
import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
	private RoleService roleService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository){
		this.userRepository = userRepository;	
	}
	
	@Autowired
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;	
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
		this.passwordEncoder = passwordEncoder;	
	}

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }
	
	public User saveUserFromDto(UserDto u){
		User user = new User();
		user.setUsername(u.getUsername());
		user.setPassword(passwordEncoder.encode(u.getPassword()));
		user.setRoles(Arrays.asList(roleService.getUserRole()));
		return save(user);
	}


}