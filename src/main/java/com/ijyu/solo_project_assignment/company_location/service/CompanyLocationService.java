package com.ijyu.solo_project_assignment.company_location.service;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_location.repository.CompanyLocationRepository;
import com.ijyu.solo_project_assignment.exception.BusinessLogicException;
import com.ijyu.solo_project_assignment.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyLocationService {
    private CompanyLocationRepository companyLocationRepository;

    public CompanyLocationService(CompanyLocationRepository companyLocationRepository) {
        this.companyLocationRepository = companyLocationRepository;
    }

    public CompanyLocation findCompanyLocation(Long companyLocationId) {
        return findVerifiedCompanyLocation(companyLocationId);
    }

    public CompanyLocation findCompanyLocationName(String companyLocationName) {
        return findVerifiedCompanyLocationName(companyLocationName);
    }

    private CompanyLocation findVerifiedCompanyLocation(Long companyLocationID) {
        Optional<CompanyLocation> findCompanyLocation = companyLocationRepository.findById(companyLocationID);
        return findCompanyLocation.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_LOCATION_NOT_FOUND));
    }

    private CompanyLocation findVerifiedCompanyLocationName(String companyLocationName) {
        Optional<CompanyLocation> findCompanyLocation = companyLocationRepository.findByCompanyLocationName(companyLocationName);
        return findCompanyLocation.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_LOCATION_NOT_FOUND));
    }
}
