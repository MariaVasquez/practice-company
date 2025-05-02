package com.practice.ada.company_service.repositories;

import com.practice.ada.company_service.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCodigoCompany(String codigoCompany);
}
