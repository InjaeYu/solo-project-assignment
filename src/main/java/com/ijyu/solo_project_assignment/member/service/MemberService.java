package com.ijyu.solo_project_assignment.member.service;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_location.service.CompanyLocationService;
import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.ijyu.solo_project_assignment.company_type.service.CompanyTypeService;
import com.ijyu.solo_project_assignment.exception.BusinessLogicException;
import com.ijyu.solo_project_assignment.exception.ExceptionCode;
import com.ijyu.solo_project_assignment.member.entity.Member;
import com.ijyu.solo_project_assignment.member.repository.MemberRepository;
import com.ijyu.solo_project_assignment.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final CompanyLocationService companyLocationService;
    private final CompanyTypeService companyTypeService;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, CompanyLocationService companyLocationService, CompanyTypeService companyTypeService, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.companyLocationService = companyLocationService;
        this.companyTypeService = companyTypeService;
        this.beanUtils = beanUtils;
    }

    @Transactional
    public Member findUser(Long memberID) {
        return findVerifiedUser(memberID);
    }

    @Transactional
    public Page<Member> findUsers(int page, int size, String type, String location) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("memberId").descending());
        if ((type == null || type.isEmpty()) && (location == null || location.isEmpty())) {
            return memberRepository.findAll(pageRequest);
        } else if (type != null && !type.isEmpty()){
            CompanyType companyType = companyTypeService.findCompanyTypeName(type);
            if(location != null && !location.isEmpty()) {
                CompanyLocation companyLocation = companyLocationService.findCompanyLocationName(location);
                return memberRepository.findALlByCompanyTypeAndCompanyLocation(pageRequest, companyType, companyLocation);
            }
            return memberRepository.findAllByCompanyType(pageRequest, companyType);
        } else if (location != null && !location.isEmpty()) {
            CompanyLocation companyLocation = companyLocationService.findCompanyLocationName(location);
            return memberRepository.findAllByCompanyLocation(pageRequest, companyLocation);
        } else throw new BusinessLogicException(ExceptionCode.FILTER_NOT_FOUND);
    }

    public Member createUser(Member member) {
        verifyExistsCompanyName(member.getCompanyName());
        return memberRepository.save(member);
    }

    public void deleteUser(Long memberId) {
        Member member = findVerifiedUser(memberId);
        memberRepository.delete(member);
    }

    public Member patchUser(Member member) {
        Member findMember = findVerifiedUser(member.getMemberId());
        return memberRepository.save(beanUtils.copyNonNullProperties(member, findMember));
    }

    private Member findVerifiedUser(Long memberId) {
        Optional<Member> findUser = memberRepository.findById(memberId);
        return findUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    private void verifyExistsCompanyName(String companyName) {
        Optional<Member> findUser = memberRepository.findByCompanyName(companyName);
        if(findUser.isPresent())
            throw new BusinessLogicException(ExceptionCode.COMPANY_NAME_EXISTS);
    }
}