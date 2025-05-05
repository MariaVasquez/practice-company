package com.practice.ada.company_service.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionResponseDTO {
    @Schema(description = "ID de la versión", example = "101")
    private Long versionId;

    @Schema(description = "Versión", example = "v1.2.3")
    private String version;

    @Schema(description = "Descripción de la versión", example = "Primera versión estable")
    private String versionDescription;
}
