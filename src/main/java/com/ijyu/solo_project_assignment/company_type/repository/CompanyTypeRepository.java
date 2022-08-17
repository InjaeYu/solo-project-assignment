package com.ijyu.solo_project_assignment.company_type.repository;

import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {
    public Optional<CompanyType> findByCompanyTypeName(String companyTypeName);
}
