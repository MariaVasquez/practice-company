package com.practice.ada.company_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRequestDto {
    private Long idCompany;
    private String codigoCompany;
    private String nameCompany;
    private String descriptionCompany;
}
