package com.javaguidance.controllers;

import com.javaguidance.exceptions.ResourceNotFoundException;
import com.javaguidance.models.User;
import com.javaguidance.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){

    return new ResponseEntity<>( userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) throws ResourceNotFoundException {
       User user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with id '"+id+"' not found"));
    return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws ResourceNotFoundException {
        User createdUser=userRepository.save(user);
        return new ResponseEntity<>(createdUser , HttpStatus.CREATED);
    }


    @PutMapping ("/api/users/{id}")
    public ResponseEntity<User> createUser(@PathVariable Integer id,@RequestBody User user) throws ResourceNotFoundException {
        User _user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        _user.setUsername(user.getUsername());
        _user.setEmail_address(user.getEmail_address());
        _user.setPassword(user.getPassword());
        _user=userRepository.save(_user);
        return new ResponseEntity<>(_user , HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) throws ResourceNotFoundException {
        userRepository.deleteById(id);

        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<HttpStatus> deleteAllUsers(@PathVariable Integer id) throws ResourceNotFoundException {
        userRepository.deleteAll();

        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
