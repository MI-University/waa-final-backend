package com.waa.backend.services;

import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.dtos.PropertyDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll();
    MessageDto getById(Long id);
    List<MessageDto> getMessagesForUserOrderByDateTimeDesc(User user);
    List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, PropertyDto propertyDto);
    MessageDto postMessage(MessageDto messageDto, User user);
}
