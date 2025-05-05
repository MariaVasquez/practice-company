package com.practice.ada.company_service.controllers;

import com.practice.ada.company_service.dtos.GenericResponseDto;
import com.practice.ada.company_service.dtos.request.CreateCompanyRequest;
import com.practice.ada.company_service.dtos.response.CompanyResponseDTO;
import com.practice.ada.company_service.errors.CustomErrorResponse;
import com.practice.ada.company_service.services.CompanyService;
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
@RequestMapping("/api/companies")
@AllArgsConstructor
@Tag(name = "Company", description = "Operaciones relacionadas con empresas")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Crear una nueva compañía")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compañía creada correctamente",
                    content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseDto<CompanyResponseDTO> createCompany(
            @Valid @RequestBody CreateCompanyRequest request) {
        return new GenericResponseDto<>(companyService.create(request));
    }

    @Operation(summary = "Listar todas las compañías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CompanyResponseDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<List<CompanyResponseDTO>> getAllCompanies() {
        return new GenericResponseDto<>(companyService.getAll());
    }

    @Operation(summary = "Obtener una compañía por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compañía encontrada",
                    content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Compañía no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<CompanyResponseDTO> getByCode(
            @Valid @PathVariable String code) {
        return new GenericResponseDto<>(companyService.getCode(code));
    }

    @Operation(summary = "Actualizar los datos de una compañía")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compañía actualizada correctamente",
                    content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Compañía no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponseDto<CompanyResponseDTO> updateCompany(
            @Valid @RequestBody CreateCompanyRequest request) {
        return new GenericResponseDto<>(companyService.update(request));
    }

    @Operation(summary = "Eliminar una compañía por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compañía eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Compañía no encontrada",
                    content = @Content(schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable String code) {
        companyService.delete(code);
    }
}
