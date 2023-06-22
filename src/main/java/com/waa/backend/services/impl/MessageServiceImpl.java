package com.waa.backend.services.impl;

import com.waa.backend.domains.Message;
import com.waa.backend.domains.Property;
import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.repositories.MessageRepository;
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
    public List<MessageDto> getAll() {
        return messageRepository.findAll().stream().map(x -> modelMapper.map(x, MessageDto.class)).toList();
    }

    @Override
    public List<MessageDto> getMessagesForUserOrderByDateTimeDesc(User user) {
        return messageRepository.findMessagesByRecipientIdOrderByDateDescTimeDesc(user.getId()).stream().map(x -> modelMapper.map(x, MessageDto.class)).toList();
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
    public MessageDto postMessage(MessageDto messageDto, User user) {
        Message message = modelMapper.map(messageDto, Message.class);
        if (messageDto.getRecipientId() != null) {
            User recipient = this.modelMapper.map(userService.getById(messageDto.getRecipientId()), User.class);
            message.setRecipient(recipient);
        }
        if (messageDto.getPropertyId() != null) {
            Property property = this.modelMapper.map(propertyService.getById(messageDto.getPropertyId()), Property.class);
            message.setProperty(property);
            message.setRecipient(property.getUser());
        }
        message.setSender(user);
        return modelMapper.map(messageRepository.save(message), MessageDto.class);
    }
}
