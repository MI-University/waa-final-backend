package com.waa.backend.services.impl;

import com.waa.backend.domains.LoggerEntry;
import com.waa.backend.dtos.LoggerEntryDto;
import com.waa.backend.services.LoggerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {
    private final JpaRepository<LoggerEntry, Long> repository;
    private final ModelMapper modelMapper;
    @Override
    public List<LoggerEntryDto> getAll() {
        return repository.findAll().stream().map(e->modelMapper.map(e, LoggerEntryDto.class)).toList();
    }

    @Override
    public LoggerEntryDto create(LoggerEntry loggerEntry) {
        return modelMapper.map(repository.save(loggerEntry), LoggerEntryDto.class);
    }
}
