package com.jmp.service.rest.converter;

import com.jmp.domain.Subscription;
import com.jmp.dto.SubscriptionResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        SubscriptionResponseDto dto = new SubscriptionResponseDto();
        dto.setId(source.getId());
        dto.setUserId(source.getUser().getId());
        dto.setStartDate(source.getStartDate());
        return dto;
    }
}