package com.waa.backend.controllers;

import com.waa.backend.apiresponse.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericCrudController<REQ, DTO, ID> {
    ResponseEntity<ApiResponse<List<DTO>>> getAll(DTO filter);

    ResponseEntity<ApiResponse<DTO>> getById(ID id);

    ResponseEntity<ApiResponse<DTO>> create(REQ entity) throws Exception;

    ResponseEntity<ApiResponse<DTO>> update(ID id, REQ entity) throws Exception;

    ResponseEntity<ApiResponse<Void>> delete(ID id) throws Exception;
}
