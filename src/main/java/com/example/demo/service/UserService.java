package com.example.demo.service;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }
    public Optional<UserEntity> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }
}
