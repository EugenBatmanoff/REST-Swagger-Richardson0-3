package com.jmp.service.rest.converter;

import com.jmp.domain.User;
import com.jmp.dto.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User source) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setSurname(source.getSurname());
        dto.setBirthday(source.getBirthday());
        return dto;
    }
}