package com.jmp.service.api;


import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscriptionById(Long id);

    List<SubscriptionResponseDto> getAllSubscriptions();
}