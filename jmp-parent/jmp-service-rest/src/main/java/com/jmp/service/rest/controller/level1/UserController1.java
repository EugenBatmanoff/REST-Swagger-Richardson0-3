package com.jmp.service.rest.controller.level1;

import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import com.jmp.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/1/users")
public class UserController1 {

    private final UserService userService;

    @Autowired
    public UserController1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @PostMapping("/update/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(id);
        return userService.updateUser(userRequestDto);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}