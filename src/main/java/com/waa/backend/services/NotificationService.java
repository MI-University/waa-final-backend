package com.waa.backend.services;

import com.waa.backend.dtos.EmailDto;


public interface NotificationService {
    boolean sentEmail(EmailDto emailDto);
}
