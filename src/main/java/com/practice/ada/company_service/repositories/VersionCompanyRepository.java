package com.practice.ada.company_service.repositories;

import com.practice.ada.company_service.entities.Company;
import com.practice.ada.company_service.entities.VersionCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionCompanyRepository extends JpaRepository<VersionCompany, Long> {
    VersionCompany findByCompany(Company company);
}
