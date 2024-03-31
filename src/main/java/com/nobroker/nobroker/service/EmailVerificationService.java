package com.nobroker.nobroker.service;

import com.nobroker.nobroker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class EmailVerificationService {
    @Autowired
    private EmailService emailService;

    //static variable
    static final Map<String, String> emailOtpMapping = new HashMap<>();//otp tempropry store

    @Autowired
    private UserService userService;
    //create method for verify the otp

    public Map<String, String> verifyOtp(String email, String otp){

        String storedOtp = emailOtpMapping.get(email); //after send and email that otp are stored

        Map<String , String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(otp)){

            //Fetch user by email and mark email as verified
            User user = userService.getUserByEmail(email);

            if (user != null ){ //there is user  exists then go to verifyEmail
                userService.verifyEmail(user);
                emailOtpMapping.remove(email);// for remove the otp from HashMap  after one time otp is verified  , so its defect
                response.put("status", "success");
                response.put("message", "Email verified successfully");
            }else {
                response.put("status", "error");
                response.put("message", "User not Found");
            }
        }else {
            response.put("status", "error");
            response.put("message", "InVaild otp");
        }
        return  response;
    }

    //send -otp-for -login
    public Map<String, String> sendOtpForLogin(String email) {
        if (userService.isEmailVerified(email)){ //email is present or not in db for email verified
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp); //again store OTP on a temperorary basis

            //Send otp to the users email
            emailService.sendOtpEmail(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return response;
        }else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email is not verified");
            return response;
        }
    }

    //verify-otp-for-login

    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email); //get otp from hashmap
        Map<String, String> response = new HashMap<>();

        if(storedOtp != null && storedOtp.equals(otp)){ //OTP is valid

           emailOtpMapping.remove(email); //removed in hashmap

            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        } else {
        //Invalid Otp
        response.put("status", "error");
        response.put("message", "Invalid OTP");

    }
        return response;
    }
}
