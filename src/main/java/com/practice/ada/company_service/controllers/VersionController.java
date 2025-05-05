package com.practice.ada.company_service.controllers;

import com.practice.ada.company_service.dtos.GenericResponseDto;
import com.practice.ada.company_service.dtos.response.VersionResponseDTO;
import com.practice.ada.company_service.errors.CustomErrorResponse;
import com.practice.ada.company_service.services.VersionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/versions")
@AllArgsConstructor
@Tag(name = "Version", description = "Operaciones relacionadas con versiones")
public class VersionController {
    private final VersionService versionService;


    @Operation(summary = "Listar todas las versiones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VersionResponseDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<List<VersionResponseDTO>> getAllVersion() {
        return new GenericResponseDto<>(versionService.getAll());
    }

}
