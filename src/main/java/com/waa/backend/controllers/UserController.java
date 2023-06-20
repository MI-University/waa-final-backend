package com.waa.backend.controllers;


import com.waa.backend.dtos.UserDto;
import com.waa.backend.request.UserRequest;
import com.waa.backend.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController extends GenericCrudControllerImpl<UserRequest, UserDto, Long, UserService> {
    UserService userService;
    public UserController(UserService userService) {
        super(userService, "User");
        this.userService = userService;
    }
}

