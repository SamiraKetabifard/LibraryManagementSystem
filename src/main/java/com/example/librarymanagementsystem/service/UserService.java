package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //getMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    //postMapping
   public User createUser(User user){
        return userRepository.save(user);
   }
}
