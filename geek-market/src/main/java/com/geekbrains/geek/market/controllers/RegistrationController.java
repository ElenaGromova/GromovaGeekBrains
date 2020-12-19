package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.UserDto;
import com.geekbrains.geek.market.exceptions.RegistrationError;
import com.geekbrains.geek.market.services.RoleService;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping(consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserDto u, BindingResult bindingResult) {
       if (userService.findByUsername(u.getUsername()).isPresent()){
		   return new ResponseEntity<>(new RegistrationError("Username " + u.getUsername() + " is busy"), HttpStatus.BAD_REQUEST);
	   }
	   if (!u.getPassword().equals(u.getConfirmPassword())){
		   return new ResponseEntity<>(new RegistrationError("Passwords not equals"), HttpStatus.BAD_REQUEST);
	   }
	   if (bindingResult.hasErrors()){
		   return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
	   }
	   userService.saveUserFromDto(u);
	   return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
