package com.waa.backend.services.impl;

import com.waa.backend.domains.Project;
import com.waa.backend.dtos.ProjectDto;
import com.waa.backend.repositories.ProjectRepository;
import com.waa.backend.services.ProjectService;
import com.waa.backend.util.ModelMapperHelper;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends GenericCrudServiceImpl<Project, ProjectDto, ProjectDto, Long> implements ProjectService {
    public ProjectServiceImpl(ProjectRepository repository, ModelMapperHelper<Project, ProjectDto> modelMapperHelper) {
        super(repository, modelMapperHelper, Project.class, ProjectDto.class);
    }
}

