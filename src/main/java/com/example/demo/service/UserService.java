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

    public UserEntity saveuser(UserEntity user) {
        return userRepository.save(user);
    }
    public Optional<UserEntity> getuserbyid(Long id){
        return userRepository.findById(id);
    }
    public Optional<UserEntity> getuserbyemail(String email){
        return userRepository.findbyemail(email);
    }
    public List<UserEntity> getallusers(){
        return userRepository.findAll();
    }
    public boolean existsbyemail(String email){
        return userRepository.existsbyemail(email);
    }
    public void deleteuser(Long id){
        userRepository.deleteById(id);
    }
}
