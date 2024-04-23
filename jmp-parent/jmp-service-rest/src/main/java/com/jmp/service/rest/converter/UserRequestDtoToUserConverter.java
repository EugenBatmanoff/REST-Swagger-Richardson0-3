package com.jmp.service.rest.converter;

import com.jmp.domain.User;
import com.jmp.dto.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto source) {
        User user = new User();
        user.setName(source.getName());
        user.setSurname(source.getSurname());
        user.setBirthday((source.getBirthday()));
        return user;
    }
}