package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.VersionDto;
import com.practice.ada.company_service.entities.Version;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ApplicationMapper.class })
public interface VersionMapper {
    VersionDto toDto(Version version);
    Version toEntity(VersionDto dto);
}
