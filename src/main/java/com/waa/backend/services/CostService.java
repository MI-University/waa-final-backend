package com.waa.backend.services;

import java.util.List;

import com.waa.backend.dtos.CostDto;

public interface CostService {
    public List<CostDto> getAll();

    public CostDto getById(Long id);

    public CostDto create(CostDto CostDto);

    public CostDto update(Long id, CostDto CostDto);

    public void delete(Long id);
}
