package com.practice.ada.company_service.services;

import com.practice.ada.company_service.dtos.request.CreateCompanyRequest;
import com.practice.ada.company_service.dtos.response.CompanyResponseDTO;

import java.util.List;

public interface CompanyService {
    CompanyResponseDTO create(CreateCompanyRequest company);
    List<CompanyResponseDTO> getAll();
    CompanyResponseDTO getCode(String code);
    CompanyResponseDTO update(CreateCompanyRequest company);
    void delete(String code);
}
