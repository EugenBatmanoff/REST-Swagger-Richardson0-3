package com.jmp.cloud.service.impl;

import com.jmp.domain.User;
import com.jmp.domain.repository.UserRepository;
import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import com.jmp.service.api.UserService;
import com.jmp.service.rest.converter.UserRequestDtoToUserConverter;
import com.jmp.service.rest.converter.UserToUserResponseDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRequestDtoToUserConverter userRequestDtoToUserConverter;
    private final UserToUserResponseDtoConverter userToUserResponseDtoConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserRequestDtoToUserConverter userRequestDtoToUserConverter,
                           UserToUserResponseDtoConverter userToUserResponseDtoConverter) {
        this.userRepository = userRepository;
        this.userRequestDtoToUserConverter = userRequestDtoToUserConverter;
        this.userToUserResponseDtoConverter = userToUserResponseDtoConverter;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
         User user = userRequestDtoToUserConverter.convert(userRequestDto);
        user = userRepository.save(user);
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        User userToUpdate = userRequestDtoToUserConverter.convert(userRequestDto);
        User existingUser = userRepository.findById(userRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userToUpdate.getName());
        existingUser.setSurname(userToUpdate.getSurname());
        existingUser.setBirthday(userToUpdate.getBirthday());
        userRepository.save(existingUser);
        return userToUserResponseDtoConverter.convert(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userToUserResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

