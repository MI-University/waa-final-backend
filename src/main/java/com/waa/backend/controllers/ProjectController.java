package com.aeontanvir.projectcosting.controllers;


import com.aeontanvir.projectcosting.apiresponse.ApiResponse;
import com.aeontanvir.projectcosting.dtos.ProjectDto;
import com.aeontanvir.projectcosting.services.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDto>>> getAll() {
        List<ProjectDto> projects = projectService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Projects retrieved successfully.", projects));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDto>> geById(@PathVariable Long id) {
        ProjectDto project = projectService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Project retrieved successfully.", project));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDto>> create(@RequestBody ProjectDto ProjectDto) {
        ProjectDto createdProject = projectService.create(ProjectDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Project created successfully.", createdProject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDto>> update(@PathVariable Long id,
            @RequestBody ProjectDto ProjectDto) {
        ProjectDto updatedProject = projectService.update(id, ProjectDto);
        return ResponseEntity.ok(ApiResponse.success("Project updated successfully.", updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Project deleted successfully.", null));
    }

}
