package com.practice.ada.company_service.controllers;

import com.practice.ada.company_service.dtos.GenericResponseDto;
import com.practice.ada.company_service.dtos.request.CreateApplicationRequest;
import com.practice.ada.company_service.dtos.response.ApplicationResponseDTO;
import com.practice.ada.company_service.errors.CustomErrorResponse;
import com.practice.ada.company_service.services.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@AllArgsConstructor
@Tag(name = "Application", description = "Operaciones relacionadas con aplicaciones")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "Crear una nueva aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aplicación creada correctamente",
                    content = @Content(schema = @Schema(implementation = ApplicationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseDto<ApplicationResponseDTO> createApplication(
            @Valid @RequestBody CreateApplicationRequest request) {
        return new GenericResponseDto<>(applicationService.create(request));
    }

    @Operation(summary = "Listar todas las aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ApplicationResponseDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<List<ApplicationResponseDTO>> getAllApplication() {
        return new GenericResponseDto<>(applicationService.getAll());
    }

    @Operation(summary = "Obtener una aplicación por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aplicación encontrada",
                    content = @Content(schema = @Schema(implementation = ApplicationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Aplicación no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<ApplicationResponseDTO> getByCodeApplication(
            @Valid @PathVariable String code) {
        return new GenericResponseDto<>(applicationService.getCode(code));
    }

    @Operation(summary = "Actualizar los datos de una aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aplicación actualizada correctamente",
                    content = @Content(schema = @Schema(implementation = ApplicationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aplicación no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<ApplicationResponseDTO> updateApplication(
            @Valid @RequestBody CreateApplicationRequest request) {
        return new GenericResponseDto<>(applicationService.update(request));
    }

    @Operation(summary = "Eliminar una Aplicación por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aplicación eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Aplicación no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable String code) {
        applicationService.delete(code);
    }
}
