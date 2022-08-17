package com.ijyu.solo_project_assignment.company_location.repository;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyLocationRepository extends JpaRepository<CompanyLocation, Long> {
    public Optional<CompanyLocation> findByCompanyLocationName(String companyLocationName);
}
