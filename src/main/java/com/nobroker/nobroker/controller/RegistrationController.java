package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.payload.UserDto;
import com.nobroker.nobroker.service.EmailService;
import com.nobroker.nobroker.service.EmailVerificationService;
import com.nobroker.nobroker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private EmailService emailService;


    private UserService userService;

    private EmailVerificationService emailVerificationService;

    public RegistrationController(EmailService emailService, UserService userService, EmailVerificationService emailVerificationService) {
        this.emailService = emailService;
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody UserDto userDto){

        //Register the User without email verification
        UserDto userDtos = userService.registerUser(userDto);

        //once user is saved in database they generate otp required
        //SEND OTP email for email verification
        Map<String, String> response = emailService.sendOtpEmail(userDtos.getEmail());

        return response;
    }

    //for email otp verification
    ///http://localhost:8080/api/verify-otp?email=&otp=
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtp(email, otp);
    }

}
