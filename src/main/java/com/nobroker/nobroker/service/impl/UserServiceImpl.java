package com.nobroker.nobroker.service.impl;

import com.nobroker.nobroker.entity.User;
import com.nobroker.nobroker.payload.UserDto;
import com.nobroker.nobroker.repository.UserRepository;
import com.nobroker.nobroker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public long createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;

    }
    UserDto mapToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;

    }
}
