package com.practice.ada.company_service.services;

import com.practice.ada.company_service.dtos.CompanyDto;
import com.practice.ada.company_service.dtos.CompanyResponseCode;
import com.practice.ada.company_service.dtos.CompanyRequestDto;

import java.util.List;

public interface CompanyService {

    List<CompanyRequestDto> getAll();
    CompanyRequestDto getById(Long id);
    CompanyResponseCode getByCode(String code);
    CompanyResponseCode create(CompanyDto accountDto);
    CompanyResponseCode update(CompanyDto accountDto);
    void deleteById(String code);
}
