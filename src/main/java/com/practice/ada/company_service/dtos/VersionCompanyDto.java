package com.practice.ada.company_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VersionCompanyDto {
    private Long versionCompanyId;
    private String versionCompanyDescription;
    @JsonIgnore
    private CompanyDto company;
    @JsonIgnore
    private VersionDto version;
}
