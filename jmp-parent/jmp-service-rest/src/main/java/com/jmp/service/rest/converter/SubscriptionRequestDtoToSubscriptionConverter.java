package com.jmp.service.rest.converter;

import com.jmp.domain.Subscription;
import com.jmp.domain.User;
import com.jmp.domain.repository.UserRepository;
import com.jmp.dto.SubscriptionRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    private final UserRepository userRepository;

    public SubscriptionRequestDtoToSubscriptionConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        Subscription subscription = new Subscription();
        User user = userRepository.findById(source.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + source.getUserId()));
        subscription.setUser(user);
        return subscription;
    }
}