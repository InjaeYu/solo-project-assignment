package com.ijyu.solo_project_assignment.company_type.service;

import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.ijyu.solo_project_assignment.company_type.repository.CompanyTypeRepository;
import com.ijyu.solo_project_assignment.exception.BusinessLogicException;
import com.ijyu.solo_project_assignment.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyTypeService {
    private CompanyTypeRepository companyTypeRepository;

    public CompanyTypeService(CompanyTypeRepository companyTypeRepository) {
        this.companyTypeRepository = companyTypeRepository;
    }

    public CompanyType findCompanyType(Long companyTypeId) {
        return findVerifiedCompanyType(companyTypeId);
    }

    public CompanyType findCompanyTypeName(String companyTypeName) {
        return findVerifiedCompanyTypeName(companyTypeName);
    }

    private CompanyType findVerifiedCompanyType(Long companyTypeId) {
        Optional<CompanyType> findCompanyType = companyTypeRepository.findById(companyTypeId);
        return findCompanyType.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_TYPE_NOT_FOUND));
    }

    private CompanyType findVerifiedCompanyTypeName(String companyTypeName) {
        Optional<CompanyType> findCompanyType = companyTypeRepository.findByCompanyTypeName(companyTypeName);
        return findCompanyType.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_TYPE_NOT_FOUND));
    }
}
