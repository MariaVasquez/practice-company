package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.CompanyDto;
import com.practice.ada.company_service.dtos.CompanyRequestDto;
import com.practice.ada.company_service.entities.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { VersionCompanyMapper.class })
public interface CompanyMapper {
    CompanyDto companyToCompanyDto(Company company);
    Company companyDtoToCompany(CompanyDto companyDto);
    CompanyRequestDto companyToCompanyRequestDto(Company company);
}