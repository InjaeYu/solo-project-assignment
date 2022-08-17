package com.ijyu.solo_project_assignment.member.repository;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.ijyu.solo_project_assignment.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByCompanyName(String companyName);
    public Page<Member> findAllByCompanyType(PageRequest pageRequest, CompanyType companyType);
    public Page<Member> findAllByCompanyLocation(PageRequest pageRequest, CompanyLocation companyLocation);
    public Page<Member> findALlByCompanyTypeAndCompanyLocation(PageRequest pageRequest, CompanyType companyType, CompanyLocation companyLocation);
    public Page<Member> findAllByCompanyTypeOrCompanyLocation(PageRequest pageRequest, CompanyType companyType, CompanyLocation companyLocation);
}