package com.waa.backend.controllers;


import com.waa.backend.dtos.ProjectDto;
import com.waa.backend.services.ProjectService;
import com.waa.backend.services.impl.ProjectServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController extends GenericCrudControllerImpl<ProjectDto, ProjectDto, Long, ProjectService> {
    public ProjectController(ProjectService projectService) {
        super(projectService, "Project");
    }
}
