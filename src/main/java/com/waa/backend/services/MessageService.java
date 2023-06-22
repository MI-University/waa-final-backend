package com.waa.backend.services;

import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto getById(Long id);
    List<MessageDto> getMessagesForUserOrderByDateTimeDesc(User user);
    List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, Long propertyDto);
    MessageDto postMessage(MessageDto messageDto, User user);
}
