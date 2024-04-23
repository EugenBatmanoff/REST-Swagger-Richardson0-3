package com.jmp.service.rest.controller.level0;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/0/service")
public class ServiceController0 {

    private final SubscriptionService subscriptionService;

    @Autowired
    public ServiceController0(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/action")
    public Object performAction(@RequestBody RequestWrapper request) {
        switch (request.getMethod()) {
            case "createSubscription":
                return subscriptionService.createSubscription((SubscriptionRequestDto) request.getParams());
            case "updateSubscription":
                return subscriptionService.updateSubscription((SubscriptionRequestDto) request.getParams());
            case "deleteSubscription":
                subscriptionService.deleteSubscription((Long) request.getParams());
                return HttpStatus.NO_CONTENT;
            case "getSubscription":
                return subscriptionService.getSubscriptionById((Long) request.getParams());
            case "getAllSubscriptions":
                return subscriptionService.getAllSubscriptions();
            default:
                throw new IllegalArgumentException("Unsupported method");
        }
    }
}
