package com.practice.ada.company_service.services.impl;

import com.practice.ada.company_service.dtos.request.CreateApplicationRequest;
import com.practice.ada.company_service.dtos.request.CreateVersionRequest;
import com.practice.ada.company_service.dtos.response.ApplicationResponseDTO;
import com.practice.ada.company_service.entities.Application;
import com.practice.ada.company_service.entities.Version;
import com.practice.ada.company_service.errors.CustomException;
import com.practice.ada.company_service.mapper.ApplicationMapper;
import com.practice.ada.company_service.mapper.VersionMapper;
import com.practice.ada.company_service.repositories.ApplicationRepository;
import com.practice.ada.company_service.repositories.VersionRepository;
import com.practice.ada.company_service.services.ApplicationService;
import com.practice.ada.company_service.util.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final VersionMapper versionMapper;

    @Override
    @Transactional
    public ApplicationResponseDTO create(CreateApplicationRequest applicationDto) {
        try {
            Application applicationValidate = applicationRepository.findByAppCode(applicationDto.getCode());
            if (applicationValidate != null) {
                throw new CustomException(ResponseCode.APPLICATION_EXIST);
            }
            Application application = applicationMapper.toEntity(applicationDto);

            List<Version> versionList = applicationDto.getVersions().stream()
                    .map(versionDto -> {
                        Version version = versionMapper.toEntity(versionDto);
                        version.setApplication(application);
                        return version;
                    })
                    .collect(Collectors.toList());

            application.setVersions(versionList);

            return applicationMapper.toResponse(applicationRepository.save(application));
        } catch (Exception e) {
            log.error("Error to create application: {}", e.getMessage(), e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDTO> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationResponseDTO getCode(String code) {
        Application application = applicationRepository.findByAppCode(code);
        if (application == null) {
            throw new CustomException(ResponseCode.APPLICATION_NOT_FOUND);
        }
        return applicationMapper.toResponse(application);
    }

    @Override
    @Transactional
    public ApplicationResponseDTO update(CreateApplicationRequest applicationDto) {
        try {
            Application applicationValidate = applicationRepository.findByAppCode(applicationDto.getCode());
            if (applicationValidate == null) {
                throw new CustomException(ResponseCode.APPLICATION_NOT_FOUND);
            }

            applicationMapper.updateEntity(applicationValidate, applicationDto);

            List<Version> versionList = applicationDto.getVersions().stream()
                    .map(versionDto -> {
                        Version version = versionMapper.toEntity(versionDto);
                        version.setApplication(applicationValidate);
                        return version;
                    })
                    .collect(Collectors.toList());

            applicationValidate.getVersions().clear();
            applicationValidate.getVersions().addAll(versionList);

            return applicationMapper.toResponse(applicationRepository.save(applicationValidate));
        } catch (Exception e) {
            log.error("Error to update application: {}", e.getMessage(), e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional
    public void delete(String code) {
        try {
            Application application = applicationRepository.findByAppCode(code);
            if (application == null) {
                throw new CustomException(ResponseCode.APPLICATION_NOT_FOUND);
            }
            applicationRepository.delete(application);

        } catch (Exception e) {
            log.error("Error to delete application: {}", e.getMessage(), e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }

    }
}
