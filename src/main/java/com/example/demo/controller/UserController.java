package com.example.demo.controller;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }
    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/email")
    public Optional<UserEntity> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }  
    @DeleteMapping("/{id}") 
    public String deleteUser(@PathVariable Long id) {
        if(userService.getUserById(id).isPresent()){
            userService.deleteUser(id);
            return "User deleted successfully!";
        }
        else{
            return "User not found!";
        }
    }

}
