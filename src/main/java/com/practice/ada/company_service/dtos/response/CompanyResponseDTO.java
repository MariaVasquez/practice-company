package com.practice.ada.company_service.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponseDTO {
    @Schema(description = "ID de la compañía", example = "10")
    private Long idCompany;

    @Schema(description = "Código interno", example = "COMP-789")
    private String codigoCompany;

    @Schema(description = "Nombre de la compañía", example = "TechCorp")
    private String nameCompany;

    @Schema(description = "Descripción", example = "Empresa dedicada al software")
    private String descriptionCompany;

    @Schema(description = "Aplicación y versión")
    private List<VersionAppDTO> versionApp;
}
