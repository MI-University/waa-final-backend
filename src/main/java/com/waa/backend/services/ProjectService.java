package com.waa.backend.services;

import com.waa.backend.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {
    public List<ProjectDto> getAll();
    public ProjectDto getById(Long id);
    public ProjectDto create(ProjectDto ProjectDto);
    public ProjectDto update(Long id, ProjectDto ProjectDto);
    public void delete(Long id);
}
