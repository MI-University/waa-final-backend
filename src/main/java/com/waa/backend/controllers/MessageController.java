package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.services.MessageService;
import com.waa.backend.services.PropertyService;
import com.waa.backend.services.UserService;
import com.waa.backend.util.AUTH;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    PropertyService propertyService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    ResponseEntity<ApiResponse<List<MessageDto>>> findAll() {
        return getMessagesForUser(Optional.empty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MessageDto>> getMessagesById(@PathVariable("id") Long id) {
        MessageDto message = this.messageService.getById(id);
        if (Objects.equals(message.getRecipient().getId(), AUTH.getUserDetails().getId()) || Objects.equals(message.getSender().getId(), AUTH.getUserDetails().getId()))
            return ResponseEntity.ok(ApiResponse.success("Offer canceled successfully.", message));
        return ResponseEntity.ok(ApiResponse.error("Message Not Found"));
    }

    @GetMapping("/user/{id}")
    ResponseEntity<ApiResponse<List<MessageDto>>> getMessagesForUser(@PathVariable Optional<Long> userId) {
        User user = getUser(userId);
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.getMessagesForUserOrderByDateTimeDesc(user)));
    }

    @GetMapping("/user/{userId}/property/{propertyId}")
    ResponseEntity<ApiResponse<List<MessageDto>>> findAllByUserProperty(@PathVariable Optional<Long> userId, @PathVariable Optional<Long> propertyId) {
        Long paramPropertyId = propertyId.orElse(null);
        PropertyDto propertyDto = null;
        if (paramPropertyId != null) {
            propertyDto = propertyService.getById(paramPropertyId);
        } else {
            return getMessagesForUser(userId);
        }
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.getMessagesForUserForPropertyOrderByDateTimeDesc(getUser(userId), propertyDto)));

    }

    @PostMapping()
    ResponseEntity<ApiResponse<MessageDto>> addMessage(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(ApiResponse.success("Message retrieved successfully.", messageService.postMessage(messageDto, AUTH.getUserDetails())));

    }

    private User getUser(Optional<Long> userId) {
        Long paramUserId = userId.orElse(null);
        User user = AUTH.getUserDetails();
        if (paramUserId !=
                null) {
            //Only admins can read all messages
            if (user.getAuthorities().stream().filter(x -> x.getAuthority().equals("ADMIN")).toList().size() == 0) {
                paramUserId = user.getId();
            } else {
                user = modelMapper.map(userService.getById(paramUserId), User.class);
            }
        } else {
            paramUserId = user.getId();
            user = modelMapper.map(userService.getById(paramUserId), User.class);
        }
        return user;
    }
}
