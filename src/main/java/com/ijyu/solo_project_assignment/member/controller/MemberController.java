package com.ijyu.solo_project_assignment.member.controller;

import com.ijyu.solo_project_assignment.company_location.service.CompanyLocationService;
import com.ijyu.solo_project_assignment.company_type.service.CompanyTypeService;
import com.ijyu.solo_project_assignment.dto.MultiResponseDto;
import com.ijyu.solo_project_assignment.dto.SingleResponseDto;
import com.ijyu.solo_project_assignment.member.dto.MemberPatchDto;
import com.ijyu.solo_project_assignment.member.dto.MemberPostDto;
import com.ijyu.solo_project_assignment.member.entity.Member;
import com.ijyu.solo_project_assignment.member.mapper.MemberMapper;
import com.ijyu.solo_project_assignment.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final CompanyLocationService companyLocationService;
    private final CompanyTypeService companyTypeService;

    public MemberController(MemberService memberService, MemberMapper memberMapper, CompanyLocationService companyLocationService, CompanyTypeService companyTypeService) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.companyLocationService = companyLocationService;
        this.companyTypeService = companyTypeService;
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody MemberPostDto requestBody) {
        Member member = memberMapper.memberPostDtoToMember(requestBody,
                companyTypeService.findCompanyType(requestBody.getCompanyTypeId()),
                companyLocationService.findCompanyLocation(requestBody.getCompanyLocationId()));
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(memberService.createUser(member))),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchUser(@PathVariable("member-id") @Positive Long userId,
                                    @RequestBody MemberPatchDto requestBody) {
        requestBody.setMemberId(userId);
        Member patchMember = memberService.patchUser(memberMapper.memberPatchDtoToMember(requestBody,
                companyTypeService.findCompanyType(requestBody.getCompanyTypeId()),
                companyLocationService.findCompanyLocation(requestBody.getCompanyLocationId())));
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(patchMember)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam int size,
                                   @Positive @RequestParam int page,
                                   @Nullable @RequestParam String type,
                                   @Nullable @RequestParam String location) {
        Page<Member> users = memberService.findUsers(page - 1, size, type, location);
        return new ResponseEntity<>(
                new MultiResponseDto<>(
                        memberMapper.membersToMemberResponsesDto(users.getContent()),
                        users),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getUser(@PathVariable("member-id") @Positive Long userId) {
        Member member = memberService.findUser(userId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteUser(@PathVariable("member-id") @Positive Long userId) {
        memberService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
