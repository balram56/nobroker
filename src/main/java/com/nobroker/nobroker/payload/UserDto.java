package com.nobroker.nobroker.payload;

import jakarta.validation.constraints.Email;

public class UserDto {
    private long id;
    private String name;
    @Email
    private String email;


    private String mobile;
    private String password;
}
