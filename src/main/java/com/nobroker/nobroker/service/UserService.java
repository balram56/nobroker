package com.nobroker.nobroker.service;

import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.payload.UserDto;

public interface UserService {

    public UserDto registerUser(UserDto userDto);

    User getUserByEmail(String email);

   public  void verifyEmail(User user);
}
