package com.waa.backend.services;

import com.waa.backend.domains.LoggerEntry;
import com.waa.backend.dtos.LoggerEntryDto;
import com.waa.backend.dtos.PropertyDto;
import com.waa.backend.dtos.PropertyFilterDto;
import com.waa.backend.request.PropertyRequest;

import java.util.List;

public interface LoggerService {
    List<LoggerEntryDto> getAll();
    LoggerEntryDto create(LoggerEntry loggerEntry);
}
