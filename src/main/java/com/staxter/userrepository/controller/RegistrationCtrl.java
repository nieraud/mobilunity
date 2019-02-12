package com.staxter.userrepository.controller;

import com.staxter.userrepository.entity.User;
import com.staxter.userrepository.repository.UserRepository;
import com.staxter.userrepository.utils.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationCtrl {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationCtrl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(name="/userservice/register",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody User userRegistration) throws UserAlreadyExistsException {
        User user = userRepository.createUser(userRegistration);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
