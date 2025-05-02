package com.practice.ada.company_service.controllers;

import com.practice.ada.company_service.dtos.CompanyDto;
import com.practice.ada.company_service.dtos.CompanyRequestDto;
import com.practice.ada.company_service.dtos.CompanyResponseCode;
import com.practice.ada.company_service.dtos.GenericResponseDto;
import com.practice.ada.company_service.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public GenericResponseDto<List<CompanyRequestDto>> getAll(){
        return new GenericResponseDto<List<CompanyRequestDto>>(companyService.getAll());
    }

    @GetMapping("/{id}")
    public GenericResponseDto<CompanyRequestDto> get(@PathVariable Long id){
        return new GenericResponseDto<CompanyRequestDto>(companyService.getById(id));
    }

    @GetMapping("/code/{code}")
    public GenericResponseDto<CompanyResponseCode> getCode(@PathVariable String code){
        return new GenericResponseDto<CompanyResponseCode>(companyService.getByCode(code));
    }

    @PostMapping
    public GenericResponseDto<CompanyResponseCode> create(@RequestBody CompanyDto accountDto){
        return new GenericResponseDto<CompanyResponseCode>(companyService.create(accountDto));
    }

    @PutMapping
    public GenericResponseDto<CompanyResponseCode> update(@RequestBody CompanyDto accountDto){
        return new GenericResponseDto<CompanyResponseCode>(companyService.update(accountDto));
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        companyService.deleteById(code);
    }


}
