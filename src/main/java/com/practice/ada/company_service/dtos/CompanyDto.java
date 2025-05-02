package com.practice.ada.company_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyDto {

    private Long idCompany;
    private String codigoCompany;
    private String nameCompany;
    private String descriptionCompany;
    private List<VersionCompanyDto> versionCompanies;
}
