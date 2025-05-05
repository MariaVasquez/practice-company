package com.practice.ada.company_service.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.practice.ada.company_service.entities.Version;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponseDTO {
    @Schema(description = "ID de la aplicación", example = "1")
    private Long appId;

    @Schema(description = "Código de la aplicación", example = "APP-001")
    private String appCode;

    @Schema(description = "Nombre de la aplicación", example = "Sistema de Reservas")
    private String appName;

    @Schema(description = "Descripción de la aplicación", example = "Description sistema de Reservas")
    private String appDescription;

    @Schema(description = "Lista de versiones")
    private List<VersionResponseDTO> versions;
}
