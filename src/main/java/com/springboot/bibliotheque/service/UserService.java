package com.springboot.bibliotheque.service;

import com.springboot.bibliotheque.entity.User;
import com.springboot.bibliotheque.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return  user.get();
    }
}
