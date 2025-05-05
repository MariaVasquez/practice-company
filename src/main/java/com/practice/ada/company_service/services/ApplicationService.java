package com.practice.ada.company_service.services;

import com.practice.ada.company_service.dtos.request.CreateApplicationRequest;
import com.practice.ada.company_service.dtos.response.ApplicationResponseDTO;

import java.util.List;

public interface ApplicationService {

    ApplicationResponseDTO create(CreateApplicationRequest applicationDto);
    List<ApplicationResponseDTO> getAll();
    ApplicationResponseDTO getCode(String code);
    ApplicationResponseDTO update(CreateApplicationRequest applicationDto);
    void delete(String code);
}
