package com.jmp.service.rest.controller.level1;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import com.jmp.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/1/subscriptions")
public class ServiceController1 {

    private final SubscriptionService subscriptionService;

    @Autowired
    public ServiceController1(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.createSubscription(subscriptionRequestDto);
    }

    @PostMapping("/update/{id}")
    public SubscriptionResponseDto updateSubscription(@PathVariable Long id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        subscriptionRequestDto.setId(id);
        return subscriptionService.updateSubscription(subscriptionRequestDto);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/{id}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @GetMapping("/list")
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
}