package com.practice.ada.company_service.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVersionRequest {
    @NotNull
    private Long id;
    @NotNull
    @Schema(description = "Versión", example = "v1.2.3")
    private String version;

    @NotNull
    @Schema(description = "Descripción de la versión", example = "Primera versión estable")
    private String description;
}
