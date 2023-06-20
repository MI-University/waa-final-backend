package com.waa.backend.services.impl;

import com.waa.backend.domains.User;
import com.waa.backend.dtos.UserDto;
import com.waa.backend.repositories.UserRepository;
import com.waa.backend.request.UserRequest;
import com.waa.backend.services.UserService;
import com.waa.backend.util.ModelMapperHelper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericCrudServiceImpl<User, UserRequest, UserDto, Long> implements UserService {
    public UserServiceImpl(UserRepository repository, ModelMapperHelper<User, UserDto> modelMapperHelper) {
        super(repository, modelMapperHelper, User.class, UserDto.class);
    }
}
