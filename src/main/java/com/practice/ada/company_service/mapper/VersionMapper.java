package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.request.CreateVersionRequest;
import com.practice.ada.company_service.dtos.response.VersionResponseDTO;
import com.practice.ada.company_service.entities.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VersionMapper {

    @Mapping(source = "id", target = "versionId")
    @Mapping(source = "description", target = "versionDescription")
    @Mapping(target = "versionCompanies", ignore = true)
    @Mapping(target = "application", ignore = true)
    Version toEntity(CreateVersionRequest dto);
    VersionResponseDTO toResponse(Version entity);
}
