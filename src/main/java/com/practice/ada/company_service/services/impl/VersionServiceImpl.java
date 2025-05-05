package com.practice.ada.company_service.services.impl;

import com.practice.ada.company_service.dtos.request.CreateVersionRequest;
import com.practice.ada.company_service.dtos.response.VersionResponseDTO;
import com.practice.ada.company_service.entities.Version;
import com.practice.ada.company_service.errors.CustomException;
import com.practice.ada.company_service.mapper.VersionMapper;
import com.practice.ada.company_service.repositories.VersionRepository;
import com.practice.ada.company_service.services.VersionService;
import com.practice.ada.company_service.util.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;
    private final VersionMapper versionMapper;

    @Override
    @Transactional
    public VersionResponseDTO create(CreateVersionRequest versionRequest) {
        try {
            Version versionValidate = versionRepository.findByVersion(versionRequest.getVersion());
            if (versionValidate != null) {
                throw new CustomException(ResponseCode.VERSION_EXIST);
            }

            Version version = versionMapper.toEntity(versionRequest);
            return versionMapper.toResponse(versionRepository.save(version));
        } catch (Exception e) {
            log.error("Error to create version: {}", e.getMessage(), e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<VersionResponseDTO> getAll() {
        return versionRepository.findAll()
                .stream()
                .map(versionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VersionResponseDTO getId(Long id) {
        Version version = versionRepository.findById(id)
                .orElseThrow();
        return versionMapper.toResponse(version);
    }

    @Override
    @Transactional
    public VersionResponseDTO update(CreateVersionRequest applicationDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Version version = versionRepository.findById(id)
                    .orElseThrow();
            versionRepository.delete(version);
        } catch (Exception e) {
            log.error("Error to delete version: {}", e.getMessage(), e);
            throw new CustomException(ResponseCode.DATABASE_ERROR);
        }
    }
}
