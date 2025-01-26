package com.springboot.bibliotheque.controller;

import com.springboot.bibliotheque.entity.User;
import com.springboot.bibliotheque.exception.UserNotFoundException;
import com.springboot.bibliotheque.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
        }

        userService.addUser(user);
        return ResponseEntity.ok("created user");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(Long id){
        try {
            return ResponseEntity.ok(userService.getUser(id));
        }catch (Exception exception){
            throw new UserNotFoundException(id);
        }
    }

}
