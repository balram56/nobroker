package com.nobroker.nobroker.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    //for generate otp build a method
    public String generateOtp(){
        //generate 6 digit random integer number for otp at email // like - otp-> 006965
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }
    //For send Otp on email
    public Map<String, String> sendOtpEmail(String email){
        //before i send and email generateOtp() method should be called. , once will call and generate 6 digit nuber and store in otp
       String otp = generateOtp();
//       //save the otp for later verification
//        emailOtpMapping.put(email, otp);

        //SEnd OTP the user's email
        sendEmail(email, "OTP foe Email verification", "Your OTP is: " );

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "OTP sent successfully");
        return response;
    }

    private void sendEmail(String to, String subject, String text){
        SimpleMailMessage  message = new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
