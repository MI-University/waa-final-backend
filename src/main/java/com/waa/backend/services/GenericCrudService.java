package com.waa.backend.services;

import java.util.List;

public interface GenericCrudService<REQ, DTO, ID> {
    List<DTO> getAll();
    DTO getById(ID id);
    DTO create(REQ entity);
    DTO update(REQ req, ID id);
    boolean delete(ID id);
}
