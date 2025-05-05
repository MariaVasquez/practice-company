package com.practice.ada.company_service.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateCompanyRequest {
    @NotBlank
    @Schema(description = "Código interno", example = "COMP-001")
    private String code;

    @NotBlank
    @Schema(description = "Nombre de la empresa", example = "TechCorp")
    private String name;

    @NotBlank
    @Schema(description = "Descripción opcional", example = "Empresa de tecnología")
    private String description;

    @NotEmpty(message = "Debe indicar al menos una versión")
    @Schema(description = "Lista de versiones asociadas")
    private List<Long> versionIds;
}
