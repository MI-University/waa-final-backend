package com.waa.backend.services;

import com.waa.backend.domains.User;
import com.waa.backend.dtos.MessageDto;
import com.waa.backend.request.MessageRequest;

import java.util.List;

public interface MessageService {
    MessageDto getById(Long id);
    List<MessageDto> getMessagesForUserOrderByDateTimeDesc(Long userId);
    List<MessageDto> getMessagesForUserForPropertyOrderByDateTimeDesc(User user, Long propertyDto);
    MessageDto postMessage(MessageRequest messageRequest, User user);
}
