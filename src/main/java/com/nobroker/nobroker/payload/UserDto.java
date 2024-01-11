package com.nobroker.nobroker.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotEmpty(message = "name should be required")
    private String name;
    @Email
    private String email;

    @Size(max = 10, min = 10, message = "mobile number should be 10 digits")
    private String mobile;
    @NotEmpty(message = "password should be required")
    private String password;
}
