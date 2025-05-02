package com.practice.ada.company_service.repositories;

import com.practice.ada.company_service.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
