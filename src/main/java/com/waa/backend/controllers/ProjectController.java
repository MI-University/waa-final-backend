package com.waa.backend.controllers;


import com.waa.backend.dtos.ProjectDto;
import com.waa.backend.services.impl.ProjectServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController extends GenericCrudControllerImpl<ProjectDto, ProjectDto, Long, ProjectServiceImpl> {
    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        super(projectServiceImpl, "Project");
    }
}
