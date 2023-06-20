package com.waa.backend.services;

import com.waa.backend.dtos.UserDto;
import com.waa.backend.request.UserRequest;

public interface UserService extends GenericCrudService<UserRequest, UserDto, Long> {
}