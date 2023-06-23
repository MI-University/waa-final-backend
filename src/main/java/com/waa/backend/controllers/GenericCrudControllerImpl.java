package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import com.waa.backend.services.GenericCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public abstract class GenericCrudControllerImpl<REQ, DTO, ID, S extends GenericCrudService<REQ, DTO, ID>> implements GenericCrudController<REQ, DTO, ID> {

    @Autowired
    private S service;
    private String modelName;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DTO>>> getAll(@ModelAttribute DTO filterData) {
        List<DTO> entities = service.getAll(filterData);
        return ResponseEntity.ok(ApiResponse.success(this.modelName + " retrieved successfully.", entities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DTO>> getById(@PathVariable ID id) {
        DTO entity = service.getById(id);
        if (entity != null) {
            return ResponseEntity.ok(ApiResponse.success(this.modelName + " retrieved successfully.", entity));
        } else {
            return ResponseEntity.ok(ApiResponse.error(this.modelName + " retrieved failed."));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DTO>> create(@Valid @RequestBody REQ entity) throws Exception {
        DTO createdEntity = service.create(entity);
        if (entity != null) {
            return ResponseEntity.ok(ApiResponse.success(this.modelName + " created successfully.", createdEntity));
        } else {
            return ResponseEntity.ok(ApiResponse.error(this.modelName + " create failed."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DTO>> update(@Valid @PathVariable ID id, @RequestBody REQ entity) throws Exception {
        DTO updatedEntity = service.update(entity, id);
        if (entity != null) {
            return ResponseEntity.ok(ApiResponse.success(this.modelName + " updated successfully.", updatedEntity));
        } else {
            return ResponseEntity.ok(ApiResponse.error(this.modelName + " update failed."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id) throws Exception {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success(this.modelName + " deleted successfully.", null));
        } else {
            return ResponseEntity.ok(ApiResponse.error(this.modelName + " update failed."));
        }
    }
}
