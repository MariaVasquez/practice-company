package com.practice.ada.company_service.services.impl;

import com.practice.ada.company_service.dtos.request.CreateCompanyRequest;
import com.practice.ada.company_service.dtos.response.CompanyResponseDTO;
import com.practice.ada.company_service.dtos.response.VersionAppDTO;
import com.practice.ada.company_service.entities.Company;
import com.practice.ada.company_service.entities.Version;
import com.practice.ada.company_service.entities.VersionCompany;
import com.practice.ada.company_service.errors.CustomException;
import com.practice.ada.company_service.mapper.CompanyMapper;
import com.practice.ada.company_service.repositories.CompanyRepository;
import com.practice.ada.company_service.repositories.VersionRepository;
import com.practice.ada.company_service.services.CompanyService;
import com.practice.ada.company_service.util.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final VersionRepository versionRepository;
    private final CompanyMapper companyMapper;

    @Override
    @Transactional
    public CompanyResponseDTO create(CreateCompanyRequest companyDto) {
        log.info("Init create company: {}", companyDto.getCode());
        try {
            Company companyValidate = companyRepository.findByCodigoCompany(companyDto.getCode());
            if (companyValidate != null) {
                throw new CustomException(ResponseCode.COMPANY_EXIST);
            }

            Company company = companyMapper.toEntity(companyDto);

            List<VersionCompany> versionCompanies = companyDto.getVersionIds().stream()
                    .map(id -> {
                        Version version = versionRepository
                                .findById(id)
                                .orElseThrow(() -> new CustomException(ResponseCode.VERSION_NOT_FOUND));

                        return VersionCompany.builder()
                                .company(company)
                                .version(version)
                                .versionCompanyDescription("company associate " + company.getCodigoCompany())
                                .build();
                    }).collect(Collectors.toList());

            company.setVersionCompanies(versionCompanies);

            return companyMapper.toResponse(companyRepository.save(company));
        } catch (Exception e) {
            log.error("Error to create company: {}", e.getMessage());
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getAll() {
        return companyRepository
                .findAll()
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDTO getCode(String code) {
        try {
            Company company = companyRepository.findByCodigoCompany(code);
            if (company == null) {
                throw new CustomException(ResponseCode.COMPANY_NOT_FOUND);
            }

            List<VersionAppDTO> versionAppList = company.getVersionCompanies()
                    .stream()
                    .map(v -> VersionAppDTO.builder()
                            .version(v.getVersion().getVersion())
                            .application(v.getVersion().getApplication().getAppName())
                            .build())
                    .toList();

            return CompanyResponseDTO.builder()
                    .nameCompany(company.getNameCompany())
                    .codigoCompany(company.getCodigoCompany())
                    .versionApp(versionAppList)
                    .build();
        } catch (Exception e) {
            log.error("Error to get company: {}", e.getMessage());
            throw new CustomException(ResponseCode.UNEXPECTED_ERROR);
        }
    }

    @Override
    @Transactional
    public CompanyResponseDTO update(CreateCompanyRequest companyDto) {
        try {
            Company companyValidate = companyRepository.findByCodigoCompany(companyDto.getCode());
            if (companyValidate == null) {
                throw new CustomException(ResponseCode.COMPANY_NOT_FOUND);
            }

            companyMapper.updateEntity(companyValidate, companyDto);

            List<VersionCompany> versionCompanies = companyDto.getVersionIds().stream()
                    .map(id -> {
                        Version version = versionRepository
                                .findById(id)
                                .orElseThrow(() -> new CustomException(ResponseCode.VERSION_NOT_FOUND));

                        return VersionCompany.builder()
                                        .company(companyValidate)
                                        .version(version)
                                        .versionCompanyDescription("company associate " + companyValidate.getCodigoCompany())
                                        .build();
                    })
                    .collect(Collectors.toList());

            companyValidate.getVersionCompanies().clear();
            companyValidate.getVersionCompanies().addAll(versionCompanies);

            return companyMapper.toResponse(companyRepository.save(companyValidate));
        } catch (Exception e) {
            log.error("Error to create company: {}", e.getMessage());
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void delete(String code) {
        log.info("Init delete company: {}", code);
        try {
            Company company = companyRepository.findByCodigoCompany(code);
            if (company == null) {
                throw new CustomException(ResponseCode.COMPANY_NOT_FOUND);
            }
            companyRepository.delete(company);
        } catch (Exception e) {
            log.error("Error to delete company: {}", e.getMessage());
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }
}
