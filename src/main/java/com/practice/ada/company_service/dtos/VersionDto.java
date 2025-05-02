package com.practice.ada.company_service.dtos;

import com.practice.ada.company_service.entities.VersionCompany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VersionDto {
    private Long versionId;
    private String version;
    private String versionDescription;
    private List<VersionCompany> versionCompanies;
    private ApplicationDto application;
}
