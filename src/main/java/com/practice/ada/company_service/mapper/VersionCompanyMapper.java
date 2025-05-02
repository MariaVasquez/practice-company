package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.VersionCompanyDto;
import com.practice.ada.company_service.entities.VersionCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { VersionMapper.class })
public interface VersionCompanyMapper {
    VersionCompany toEntity(VersionCompanyDto dto);
    VersionCompanyDto toDto(VersionCompany entity);
}
