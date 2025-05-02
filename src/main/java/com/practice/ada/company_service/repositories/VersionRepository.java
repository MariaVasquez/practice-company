package com.practice.ada.company_service.repositories;

import com.practice.ada.company_service.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {
}
