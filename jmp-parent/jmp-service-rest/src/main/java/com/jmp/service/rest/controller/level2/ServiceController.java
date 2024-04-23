package com.jmp.service.rest.controller.level2;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import com.jmp.service.api.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subscriptions", description = "Subscriptions management APIs")
@RestController
@RequestMapping("/subscriptions")
public class ServiceController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public ServiceController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Operation(summary = "Create a subscription", description = "Creates a new subscription for a user.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Subscription created successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.createSubscription(subscriptionRequestDto);
    }

    @Operation(summary = "Update a subscription", description = "Updates an existing subscription based on the subscription ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscription updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Subscription not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public SubscriptionResponseDto updateSubscription(@PathVariable Long id, @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        subscriptionRequestDto.setId(id);
        return subscriptionService.updateSubscription(subscriptionRequestDto);
    }

    @Operation(summary = "Delete a subscription", description = "Deletes a subscription based on the subscription ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Subscription deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @Operation(summary = "Get a subscription by ID", description = "Retrieves a subscription by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscription retrieved successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @GetMapping("/{id}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @Operation(summary = "Get all subscriptions", description = "Retrieves all subscriptions.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Subscriptions retrieved successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))})
    })
    @GetMapping
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
}