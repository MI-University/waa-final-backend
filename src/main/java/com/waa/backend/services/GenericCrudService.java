package com.waa.backend.services;

import java.util.List;

public interface GenericCrudService<REQ, DTO, ID> {
    List<DTO> getAll(DTO filterData);
    DTO getById(ID id);
    DTO create(REQ entity) throws Exception;
    DTO update(REQ req, ID id) throws Exception;
    boolean delete(ID id) throws Exception;
}
