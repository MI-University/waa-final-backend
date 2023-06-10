package com.waa.backend.services.impl;

import com.waa.backend.domains.Project;
import com.waa.backend.dtos.ProjectDto;
import com.waa.backend.exceptions.ResourceNotFoundException;
import com.waa.backend.helpers.ModelMapperHelper;
import com.waa.backend.repositories.ProjectRepository;
import com.waa.backend.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapperHelper modelMapperHelper;
    

    public List<ProjectDto> getAll() {
        List<Project> projects = projectRepository.findAll();
        return modelMapperHelper.convertToDtoList(projects, ProjectDto.class);
    }

    public ProjectDto getById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return modelMapperHelper.convertToDto(project, ProjectDto.class);
    }

    public ProjectDto create(ProjectDto projectDto) {
        Project project = modelMapperHelper.convertToEntity(projectDto, Project.class);
        Project savedProject = projectRepository.save(project);
        return modelMapperHelper.convertToDto(savedProject, ProjectDto.class);
    }

    public ProjectDto update(Long id, ProjectDto ProjectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        existingProject.setTitle(ProjectDto.getTitle());
        existingProject.setDescription(ProjectDto.getDescription());
        existingProject.setBudget(ProjectDto.getBudget());

        Project updatedProject = projectRepository.save(existingProject);
        return modelMapperHelper.convertToDto(updatedProject, ProjectDto.class);
    }

    public void delete(Long id) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        projectRepository.delete(existingProject);
    }
}
