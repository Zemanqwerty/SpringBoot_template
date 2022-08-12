package com.example.SpringBoot_test_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot_test_app.entity.UserEntity;
import com.example.SpringBoot_test_app.exception.UserAllreadyExistException;
import com.example.SpringBoot_test_app.exception.UserNotFoundException;
import com.example.SpringBoot_test_app.repository.UserRepository;
import com.example.SpringBoot_test_app.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok().body(userService.registration(user));
        } catch (UserAllreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @GetMapping("/get_all")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok().body(userRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server got error");
        }
    }


    @GetMapping("/get_user")
    public ResponseEntity getUser(@RequestParam String username) {
        try {
            return ResponseEntity.ok().body(userService.getUser(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server got error");
        }
    }

    @DeleteMapping("/delete_user/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(userService.delete(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
