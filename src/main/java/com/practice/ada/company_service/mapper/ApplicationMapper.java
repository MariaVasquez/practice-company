package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.request.CreateApplicationRequest;
import com.practice.ada.company_service.dtos.response.ApplicationResponseDTO;
import com.practice.ada.company_service.entities.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "name", target = "appName")
    @Mapping(source = "code", target = "appCode")
    @Mapping(source = "description", target = "appDescription")
    @Mapping(target = "appId", ignore = true)
    @Mapping(target = "versions", ignore = true)
    Application toEntity(CreateApplicationRequest dto);
    ApplicationResponseDTO toResponse(Application entity);

    @Mapping(source = "name", target = "appName")
    @Mapping(source = "code", target = "appCode")
    @Mapping(source = "description", target = "appDescription")
    @Mapping(target = "versions", ignore = true)
    void updateEntity(@MappingTarget Application target, CreateApplicationRequest source);

}
