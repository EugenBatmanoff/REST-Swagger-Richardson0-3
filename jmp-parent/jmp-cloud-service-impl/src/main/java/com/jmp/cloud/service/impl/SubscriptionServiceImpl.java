package com.jmp.cloud.service.impl;

import com.jmp.domain.Subscription;
import com.jmp.domain.repository.SubscriptionRepository;
import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import com.jmp.service.api.SubscriptionService;
import com.jmp.service.rest.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.jmp.service.rest.converter.SubscriptionToSubscriptionResponseDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter;
    private final SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter,
                                   SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionRequestDtoToSubscriptionConverter = subscriptionRequestDtoToSubscriptionConverter;
        this.subscriptionToSubscriptionResponseDtoConverter = subscriptionToSubscriptionResponseDtoConverter;
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscriptionToUpdate = subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto);
        Subscription existingSubscription = subscriptionRepository.findById(subscriptionRequestDto.getId())
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        existingSubscription.setUser(subscriptionToUpdate.getUser());
        subscriptionRepository.save(existingSubscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(existingSubscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionResponseDto getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(subscriptionToSubscriptionResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }
}