package com.aeontanvir.projectcosting.services.impl;

import com.aeontanvir.projectcosting.domains.Cost;
import com.aeontanvir.projectcosting.dtos.CostDto;
import com.aeontanvir.projectcosting.exceptions.ResourceNotFoundException;
import com.aeontanvir.projectcosting.helpers.ModelMapperHelper;
import com.aeontanvir.projectcosting.repositories.CostRepository;
import com.aeontanvir.projectcosting.services.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {
    private final CostRepository costRepository;
    private final ModelMapperHelper modelMapperHelper;
    

    public List<CostDto> getAll() {
        List<Cost> costs = costRepository.findAll();
        return modelMapperHelper.convertToDtoList(costs, CostDto.class);
    }

    public CostDto getById(Long id) {
        Cost cost = costRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not found with id: " + id));
        return modelMapperHelper.convertToDto(cost, CostDto.class);
    }

    public CostDto create(CostDto costDto) {
        Cost cost = modelMapperHelper.convertToEntity(costDto, Cost.class);
        Cost savedCost = costRepository.save(cost);
        return modelMapperHelper.convertToDto(savedCost, CostDto.class);
    }

    public CostDto update(Long id, CostDto CostDto) {
        Cost existingCost = costRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not found with id: " + id));

        existingCost.setTitle(CostDto.getTitle());
        existingCost.setDescription(CostDto.getDescription());
        existingCost.setAmount(CostDto.getAmount());

        Cost updatedCost = costRepository.save(existingCost);
        return modelMapperHelper.convertToDto(updatedCost, CostDto.class);
    }

    public void delete(Long id) {
        Cost existingCost = costRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not found with id: " + id));

        costRepository.delete(existingCost);
    }
}
