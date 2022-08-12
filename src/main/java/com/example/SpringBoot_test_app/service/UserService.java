package com.example.SpringBoot_test_app.service;
// import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBoot_test_app.entity.UserEntity;
import com.example.SpringBoot_test_app.exception.UserAllreadyExistException;
import com.example.SpringBoot_test_app.exception.UserNotFoundException;
import com.example.SpringBoot_test_app.repository.UserRepository;
// import com.google.common.hash.Hashing;
import com.example.SpringBoot_test_app.model.User;
import com.example.SpringBoot_test_app.model.Response;
// import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepositiry;

    public Response registration(UserEntity user) throws UserAllreadyExistException {
        if (userRepositiry.findByUsername(user.getUsername()) != null) {
            throw new UserAllreadyExistException("User " + user.getUsername() + " already registered!");
        }
        // String sha256hex = Hashing.sha256()
        //     .hashString(user.getPassword(), StandardCharsets.UTF_8)
        //     .toString();
        
        // user.setPassword(sha256hex);
        userRepositiry.save(user);
        return Response.toModel(200, "success", "user " + user.getUsername() + " created");
    }

    public User getUser(String username) throws UserNotFoundException {
        UserEntity user = userRepositiry.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User " + username + " not found!");
        }
        return User.toModel(user);
    }

    public String delete(String username) throws UserNotFoundException {
        UserEntity user = userRepositiry.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User " + username + " not found!");
        }
        
        userRepositiry.deleteById(user.getId()); 
        return username;
    }
}
