package com.waa.backend.controllers;

import com.waa.backend.dtos.UserDto;
import com.waa.backend.services.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test") // Base URL mapping
public class TestController extends GenericCrudControllerImpl<UserDto, UserDto, Long, TestServiceImpl> {
    public TestController(TestServiceImpl testServiceImpl) {
        super(testServiceImpl, "Test");
    }
}
