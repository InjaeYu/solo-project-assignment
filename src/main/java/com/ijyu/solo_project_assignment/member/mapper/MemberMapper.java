package com.ijyu.solo_project_assignment.member.mapper;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.ijyu.solo_project_assignment.member.dto.MemberPatchDto;
import com.ijyu.solo_project_assignment.member.dto.MemberPostDto;
import com.ijyu.solo_project_assignment.member.dto.MemberResponseDto;
import com.ijyu.solo_project_assignment.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    default Member memberPostDtoToMember(MemberPostDto memberPostDto, CompanyType companyType, CompanyLocation companyLocation) {
        if(memberPostDto == null) {
            return null;
        } else {
            return Member.builder()
                    .name(memberPostDto.getName())
                    .password(memberPostDto.getPassword())
                    .sex(memberPostDto.getSex())
                    .companyName(memberPostDto.getCompanyName())
                    .companyType(companyType)
                    .companyLocation(companyLocation)
                    .build();
        }
    }

    default Member memberPatchDtoToMember(MemberPatchDto memberPatchDto, CompanyType companyType, CompanyLocation companyLocation) {
        if(memberPatchDto == null) {
            return null;
        } else {
            return Member.builder()
                    .memberId(memberPatchDto.getMemberId())
                    .name(memberPatchDto.getName())
                    .password(memberPatchDto.getPassword())
                    .sex(memberPatchDto.getSex())
                    .companyName(memberPatchDto.getCompanyName())
                    .companyType(companyType)
                    .companyLocation(companyLocation)
                    .build();
        }
    }

    default MemberResponseDto memberToMemberResponseDto(Member member) {
        if(member == null) {
            return null;
        } else {
            return MemberResponseDto.builder()
                    .memberId(member.getMemberId())
                    .name(member.getName())
                    .companyName(member.getCompanyName())
                    .sex(member.getSex())
                    .companyLocationName(member.getCompanyLocation().getCompanyLocationName())
                    .companyTypeName(member.getCompanyType().getCompanyTypeName())
                    .build();
        }
    };

    List<MemberResponseDto> membersToMemberResponsesDto(List<Member> members);
}
