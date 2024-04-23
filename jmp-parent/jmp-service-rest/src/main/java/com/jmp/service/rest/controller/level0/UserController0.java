package com.jmp.service.rest.controller.level0;

import com.jmp.dto.UserRequestDto;
import com.jmp.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/0/user")
public class UserController0 {

    private final UserService userService;

    @Autowired
    public UserController0(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/action")
    public Object performAction(@RequestBody RequestWrapper request) {
        switch (request.getMethod()) {
            case "createUser":
                return userService.createUser((UserRequestDto) request.getParams());
            case "updateUser":
                return userService.updateUser((UserRequestDto) request.getParams());
            case "deleteUser":
                userService.deleteUser((Long) request.getParams());
                return HttpStatus.NO_CONTENT;
            case "getUser":
                return userService.getUserById((Long) request.getParams());
            case "getAllUsers":
                return userService.getAllUsers();
            default:
                throw new IllegalArgumentException("Unsupported method");
        }
    }
}
