package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.payload.UserDto;
import com.nobroker.nobroker.service.EmailService;
import com.nobroker.nobroker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    //http://localhost:8080/api/register
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody UserDto userDto){

        //Register the User without email verification
        UserDto userDtos = userService.registerUser(userDto);

        //once user is saved in database they generate otp required
        //SEND OTP email for email verification
        Map<String, String> response = emailService.sendOtpEmail(userDto.getEmail());

        return response;
    }

}
