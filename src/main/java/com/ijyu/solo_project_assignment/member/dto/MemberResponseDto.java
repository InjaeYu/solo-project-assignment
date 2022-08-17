package com.ijyu.solo_project_assignment.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String sex;
    private String companyName;
    private String companyTypeName;
    private String companyLocationName;

    @Builder
    public MemberResponseDto(Long memberId, String name, String sex, String companyName, String companyTypeName, String companyLocationName) {
        this.memberId = memberId;
        this.name = name;
        this.sex = sex;
        this.companyName = companyName;
        this.companyTypeName = companyTypeName;
        this.companyLocationName = companyLocationName;
    }
}
