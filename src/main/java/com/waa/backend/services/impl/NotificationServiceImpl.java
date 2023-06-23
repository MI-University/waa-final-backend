package com.waa.backend.services.impl;

import com.waa.backend.dtos.EmailDto;
import com.waa.backend.services.NotificationService;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {
    public boolean sentEmail(EmailDto emailDto) {

        // call email service
        return true;
    }
}
