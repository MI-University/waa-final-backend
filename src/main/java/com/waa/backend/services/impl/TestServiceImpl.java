package com.waa.backend.services.impl;

import com.waa.backend.domains.User;
import com.waa.backend.dtos.UserDto;
import com.waa.backend.services.TestService;
import com.waa.backend.util.ModelMapperHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service

public class TestServiceImpl extends GenericCrudServiceImpl<User, UserDto, UserDto, Long> implements TestService {
    public TestServiceImpl(JpaRepository<User, Long> repository, ModelMapperHelper<User, UserDto> modelMapperHelper) {
        super(repository, modelMapperHelper, User.class, UserDto.class);
    }
}
