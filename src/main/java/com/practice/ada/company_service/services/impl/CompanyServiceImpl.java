package com.practice.ada.company_service.services.impl;

import com.practice.ada.company_service.dtos.CompanyDto;
import com.practice.ada.company_service.dtos.CompanyResponseCode;
import com.practice.ada.company_service.dtos.CompanyRequestDto;
import com.practice.ada.company_service.entities.Application;
import com.practice.ada.company_service.entities.Company;
import com.practice.ada.company_service.entities.Version;
import com.practice.ada.company_service.entities.VersionCompany;
import com.practice.ada.company_service.errors.CustomException;
import com.practice.ada.company_service.mapper.CompanyMapper;
import com.practice.ada.company_service.repositories.ApplicationRepository;
import com.practice.ada.company_service.repositories.CompanyRepository;
import com.practice.ada.company_service.repositories.VersionCompanyRepository;
import com.practice.ada.company_service.repositories.VersionRepository;
import com.practice.ada.company_service.services.CompanyService;
import com.practice.ada.company_service.util.ResponseCode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final VersionRepository versionRepository;
    private final ApplicationRepository applicationRepository;
    private final VersionCompanyRepository versionCompanyRepository;

    @Override
    public List<CompanyRequestDto> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::companyToCompanyRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyRequestDto getById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::companyToCompanyRequestDto)
                .orElseThrow(() -> new CustomException(ResponseCode.COMPANY_NOT_FOUND));
    }

    @Override
    public CompanyResponseCode getByCode(String code) {
        try {
            Company company = companyRepository.findByCodigoCompany(code);
            if (company == null) {
                throw new CustomException(ResponseCode.COMPANY_NOT_FOUND);
            }

            VersionCompany versionCompany = versionCompanyRepository.findByCompany(company);
            Version version = versionRepository.findById(versionCompany.getVersion().getVersionId())
                    .orElseThrow(() -> new CustomException(ResponseCode.VERSION_NOT_FOUND));

            Application application = applicationRepository.findById(version.getApplication().getAppId())
                    .orElseThrow(() -> new CustomException(ResponseCode.ACCOUNT_NOT_FOUND));

            return CompanyResponseCode.builder()
                    .codigoCompany(company.getCodigoCompany())
                    .nameCompany(company.getNameCompany())
                    .appName(application.getAppName())
                    .version(version.getVersion())
                    .build();
        } catch (Exception e) {
            log.error("Create company service error", e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    public CompanyResponseCode create(CompanyDto companyDto) {
        try {
            Company company = companyMapper.companyDtoToCompany(companyDto);
            for (VersionCompany vc : company.getVersionCompanies()) {
                vc.setCompany(company);
                Long versionId = vc.getVersion().getVersionId();
                Version version = versionRepository.findById(versionId)
                        .orElseThrow(() -> new CustomException(ResponseCode.VERSION_NOT_FOUND));
                vc.setVersion(version);
            }
            Company savedCompany = companyRepository.save(company);
            log.info("ID generado: {}", savedCompany.getIdCompany());
            return CompanyResponseCode.builder()
                    .codigoCompany(savedCompany.getCodigoCompany())
                    .nameCompany(savedCompany.getNameCompany()).build();
        } catch (Exception e) {
            log.error("Create company service error", e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    public CompanyResponseCode update(CompanyDto companyDto) {
        companyRepository.findById(companyDto.getIdCompany()).orElseThrow(() -> new CustomException(ResponseCode.COMPANY_NOT_FOUND));
        try {
            Company company = companyMapper.companyDtoToCompany(companyDto);
            for (VersionCompany vc : company.getVersionCompanies()) {
                vc.setCompany(company);
                Long versionId = vc.getVersion().getVersionId();
                Version version = versionRepository.findById(versionId)
                        .orElseThrow(() -> new CustomException(ResponseCode.VERSION_NOT_FOUND));
                vc.setVersion(version);
            }
            Company savedCompany = companyRepository.save(company);
            log.info("ID generado: {}", savedCompany.getIdCompany());
            return CompanyResponseCode.builder()
                    .codigoCompany(savedCompany.getCodigoCompany())
                    .nameCompany(savedCompany.getNameCompany()).build();
        } catch (Exception e) {
            log.error("Edit company service error: {}", e.getMessage());
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    public void deleteById(String code) {
        log.info("Init delete for companyId {}", code);
        Company company = companyRepository.findByCodigoCompany(code);
        if (company == null) {
            throw new CustomException(ResponseCode.COMPANY_NOT_FOUND);
        }

        try {
            companyRepository.delete(company);
        } catch (Exception e) {
            log.error("Delete by id company service error: {}", e.getMessage());
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }
}
