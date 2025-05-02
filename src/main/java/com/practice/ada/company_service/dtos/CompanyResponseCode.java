package com.practice.ada.company_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyResponseCode {

    private String codigoCompany;
    private String nameCompany;
    private String appName;
    private String version;
}
