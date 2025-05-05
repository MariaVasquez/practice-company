package com.practice.ada.company_service.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateApplicationRequest {
    @NotBlank
    @Schema(description = "Código de la aplicación", example = "APP-001")
    private String code;

    @NotBlank
    @Schema(description = "Nombre de la aplicación", example = "Sistema de Reservas")
    private String name;

    @NotBlank
    @Schema(description = "Descripción de la aplicación", example = "Description sistema de Reservas")
    private String description;

    @NotEmpty(message = "Debe indicar al menos una versión")
    @Schema(description = "Lista de versiones")
    private List<CreateVersionRequest> versions;
}
