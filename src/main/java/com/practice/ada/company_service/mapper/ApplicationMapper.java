package com.practice.ada.company_service.mapper;

import com.practice.ada.company_service.dtos.ApplicationDto;
import com.practice.ada.company_service.entities.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDto toDto(Application application);
    Application toEntity(ApplicationDto dto);
}
