package com.waa.backend.services.impl;

import com.waa.backend.domains.Message;
import com.waa.backend.domains.Property;
import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.repositories.MessageRepository;
import com.waa.backend.request.MessageRequest;
import com.waa.backend.services.MessageService;
import com.waa.backend.services.PropertyService;
import com.waa.backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;


    @Autowired
    UserService userService;


    @Autowired
    PropertyService propertyService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MessageDto> getMessagesForUserOrderByDateTimeDesc(Long userId) {
        return messageRepository.findMessagesByRecipientIdOrderByDateDescTimeDesc(userId)
                .stream()
                .map(x -> modelMapper.map(x, MessageDto.class))
                .toList();
    }

    @Override
    public List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, Long propertyId) {
        return messageRepository.findMessagesByRecipientIdAndPropertyIdOrderByDateDescTimeDesc(
                user.getId(), propertyId).stream().map(x -> modelMapper.map(x, MessageDto.class)).toList();
    }

    @Override
    public MessageDto getById(Long id) {
        Message message = messageRepository.findById(id).orElseGet(null);
        return modelMapper.map(message, MessageDto.class);
    }

    @Override
    public MessageDto postMessage(MessageRequest messageRequest, User user) {
        Message message = modelMapper.map(messageRequest, Message.class);
        if (messageRequest.getRecipientId() != null) {
            User recipient = this.modelMapper.map(userService.getById(messageRequest.getRecipientId()), User.class);
            message.setRecipient(recipient);
        }
        if (messageRequest.getPropertyId() != null) {
            Property property = this.modelMapper.map(propertyService.getById(messageRequest.getPropertyId()), Property.class);
            message.setProperty(property);
            message.setRecipient(property.getUser());
        }
        message.setSender(user);
        return modelMapper.map(messageRepository.save(message), MessageDto.class);
    }
}
