package com.practice.ada.company_service.services;

import com.practice.ada.company_service.dtos.request.CreateVersionRequest;
import com.practice.ada.company_service.dtos.response.VersionResponseDTO;

import java.util.List;

public interface VersionService {

    VersionResponseDTO create(CreateVersionRequest applicationDto);
    List<VersionResponseDTO> getAll();
    VersionResponseDTO getId(Long id);
    VersionResponseDTO update(CreateVersionRequest applicationDto);
    void delete(Long id);
}
