package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    //For signUp user and save the userData in dataBase
    @PostMapping

    public ResponseEntity<String> createUser(
            @Valid @RequestBody UserDto userDto,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String message = bindingResult.getFieldError().getDefaultMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        long userId = userService.createUser(userDto);
        return new ResponseEntity<>("User is created and user id is :" +userId, HttpStatus.CREATED);

    }


}
