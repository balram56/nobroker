package com.nobroker.nobroker.controller;

import com.nobroker.nobroker.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    //for login via email otp
    @Autowired
    private EmailVerificationService emailVerificationService;

    //Send OTP for Login
    //http://localhost:8080/api/send-otp-for-login?email=bkp5693@gmail.com`
    @PostMapping("/send-otp-for-login")
    public Map<String, String> sendOtpForLogin(@RequestParam String email){
        return emailVerificationService.sendOtpForLogin(email);
    }
    //verify-otp-for-login
    //http://localhost:8080/api/verify-otp-for-login?email=bkp5693@gmail.com&otp=
    @PostMapping("/verify-otp-for-login")
    public Map<String, String> verifyOtpLogin(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtpForLogin(email , otp);
    }
}
