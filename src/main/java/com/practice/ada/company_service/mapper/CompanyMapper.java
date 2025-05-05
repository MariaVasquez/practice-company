package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.request.CreateCompanyRequest;
import com.practice.ada.company_service.dtos.response.CompanyResponseDTO;
import com.practice.ada.company_service.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(source = "name", target = "nameCompany")
    @Mapping(source = "code", target = "codigoCompany")
    @Mapping(source = "description", target = "descriptionCompany")
    @Mapping(target = "idCompany", ignore = true)
    @Mapping(target = "versionCompanies", ignore = true)
    Company toEntity(CreateCompanyRequest dto);
    CompanyResponseDTO toResponse(Company entity);

    void updateEntity(@MappingTarget Company entity, CreateCompanyRequest dto);
}
