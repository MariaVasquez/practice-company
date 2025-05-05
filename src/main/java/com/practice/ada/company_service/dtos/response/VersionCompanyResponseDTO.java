package com.practice.ada.company_service.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionCompanyResponseDTO {
    @Schema(description = "ID del registro", example = "500")
    private Long id;

    @Schema(description = "Descripción de la versión por compañía", example = "Versión adaptada para cliente X")
    private String description;

    @Schema(description = "Nombre de la compañía", example = "TechCorp")
    private String companyName;

    @Schema(description = "Versión", example = "v1.2.3")
    private String version;

    @Schema(description = "Nombre de la aplicación", example = "Sistema de Reservas")
    private String applicationName;
}
