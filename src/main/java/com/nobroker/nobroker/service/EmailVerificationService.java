package com.nobroker.nobroker.service;

import com.nobroker.nobroker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class EmailVerificationService {

    //static variable
    static final Map<String, String> emailOtpMapping = new HashMap<>();

    @Autowired
    private UserService userService;
    //create method for verify the otp

    public Map<String, String> verifyOtp(String email, String otp){

        String storedOtp = emailOtpMapping.get(email); //after send and email that otp are stored

        Map<String , String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(otp)){

            //Fetch user by email and mark email as verified
            User user = userService.getUserByEmail(email);

            if (user != null ){ //there is user  exists then go to verifyemail
                userService.verifyEmail(user);
                response.put("status", "success");
                response.put("message", "Email verified successfully");
            }else {
                response.put("status", "error");
                response.put("message", "User not Found");
            }
        }else {
            response.put("status", "error");
            response.put("message", "Invaild otp");
        }
        return  response;
    }

}
