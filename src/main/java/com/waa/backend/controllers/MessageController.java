package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.request.MessageRequest;
import com.waa.backend.services.MessageService;
import com.waa.backend.util.AUTH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping
    ResponseEntity<ApiResponse<List<MessageDto>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.getMessagesForUserOrderByDateTimeDesc(AUTH.getUserDetails().getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MessageDto>> getMessagesById(@PathVariable("id") Long id) {
        MessageDto message = this.messageService.getById(id);
        if (Objects.equals(message.getRecipient().getId(), AUTH.getUserDetails().getId()) || Objects.equals(message.getSender().getId(), AUTH.getUserDetails().getId()))
            return ResponseEntity.ok(ApiResponse.success("Offer canceled successfully.", message));
        else
            return ResponseEntity.ok(ApiResponse.error("Message Not Found"));
    }

    @GetMapping("/user")
    ResponseEntity<ApiResponse<List<MessageDto>>> getMessagesForUser() {
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.getMessagesForUserOrderByDateTimeDesc(AUTH.getUserDetails().getId())));
    }

    @GetMapping("/user/{userId}/property/{propertyId}")
    ResponseEntity<ApiResponse<List<MessageDto>>> findAllByUserProperty(@PathVariable Optional<Long> propertyId) {
        Long paramPropertyId = propertyId.orElse(null);
        if (paramPropertyId != null) {
            return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.",
                    messageService.getMessagesForUserForPropertyOrderByDateTimeDesc(
                            AUTH.getUserDetails(), paramPropertyId)
            ));
        } else {
            return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.getMessagesForUserOrderByDateTimeDesc(AUTH.getUserDetails().getId())));
        }
    }

    @PostMapping()
    ResponseEntity<ApiResponse<MessageDto>> addMessage(@RequestBody MessageRequest messageRequest) {
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.postMessage(messageRequest, AUTH.getUserDetails())));

    }
}
