package com.jmp.service.rest.controller.level3;

import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import com.jmp.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/3/users")
public class UserController3 {

    private final UserService userService;

    @Autowired
    public UserController3(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<UserResponseDto>> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.createUser(userRequestDto);
        EntityModel<UserResponseDto> resource = EntityModel.of(user);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getUser(user.getId())).withRel("self"));
        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(id);
        UserResponseDto updatedUser = userService.updateUser(userRequestDto);
        EntityModel<UserResponseDto> resource = EntityModel.of(updatedUser);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getUser(id)).withRel("self"));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDto>> getUser(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        EntityModel<UserResponseDto> resource = EntityModel.of(user);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getUser(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getAllUsers()).withRel("all-users"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<UserResponseDto>>> getAllUsers() {
        List<EntityModel<UserResponseDto>> users = userService.getAllUsers().stream()
                .map(user -> EntityModel.of(user,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getUser(user.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController3.class).getAllUsers()).withRel("self")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}
