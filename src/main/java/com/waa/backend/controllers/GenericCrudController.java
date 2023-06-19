package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericCrudController<REQ, DTO, ID> {
    ResponseEntity<ApiResponse<List<DTO>>> getAll();

    ResponseEntity<ApiResponse<DTO>> getById(ID id);

    ResponseEntity<ApiResponse<DTO>> create(REQ entity);

    ResponseEntity<ApiResponse<DTO>> update(ID id, REQ entity);

    ResponseEntity<ApiResponse<Void>> delete(ID id);
}
