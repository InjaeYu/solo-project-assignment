package com.ijyu.solo_project_assignment.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MemberPatchDto {
    @Setter
    private Long memberId;
    private String name;
    private String password;
    private String sex;
    private String companyName;
    private Long companyTypeId;
    private Long companyLocationId;

    @Builder
    public MemberPatchDto(Long memberId, String name, String password, String sex, String companyName, Long companyTypeId, Long companyLocationId) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.companyName = companyName;
        this.companyTypeId = companyTypeId;
        this.companyLocationId = companyLocationId;
    }
}
