package com.jmp.service.rest.controller.level3;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import com.jmp.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/3/subscriptions")
public class ServiceController3 {

    private final SubscriptionService subscriptionService;

    @Autowired
    public ServiceController3(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<SubscriptionResponseDto>> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto subscription = subscriptionService.createSubscription(subscriptionRequestDto);
        EntityModel<SubscriptionResponseDto> resource = EntityModel.of(subscription);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getSubscription(subscription.getId())).withRel("self"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getAllSubscriptions()).withRel("all-subscriptions"));
        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<SubscriptionResponseDto>> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        subscriptionRequestDto.setId(id);
        SubscriptionResponseDto updatedSubscription = subscriptionService.updateSubscription(subscriptionRequestDto);
        EntityModel<SubscriptionResponseDto> resource = EntityModel.of(updatedSubscription);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getSubscription(id)).withRel("self"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getAllSubscriptions()).withRel("all-subscriptions"));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SubscriptionResponseDto>> getSubscription(@PathVariable Long id) {
        SubscriptionResponseDto subscription = subscriptionService.getSubscriptionById(id);
        EntityModel<SubscriptionResponseDto> resource = EntityModel.of(subscription);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getSubscription(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getAllSubscriptions()).withRel("all-subscriptions"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<SubscriptionResponseDto>>> getAllSubscriptions() {
        List<EntityModel<SubscriptionResponseDto>> subscriptions = subscriptionService.getAllSubscriptions().stream()
                .map(subscription -> EntityModel.of(subscription,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getSubscription(subscription.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServiceController3.class).getAllSubscriptions()).withRel("self")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptions);
    }
}