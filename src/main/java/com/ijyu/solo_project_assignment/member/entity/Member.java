package com.ijyu.solo_project_assignment.member.entity;

import com.ijyu.solo_project_assignment.company_location.entity.CompanyLocation;
import com.ijyu.solo_project_assignment.company_type.entity.CompanyType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;

    @Column
    @NotNull
    @Setter
    String name;

    @Column
    @NotNull
    @Setter
    String password;

    @Column
    @NotNull
    @Setter
    String sex;

    @Column
    @NotNull
    @Setter
    String companyName;

    @OneToOne
    @JoinColumn(name = "COMPANY_TYPE_ID")
    @NotNull
    @Setter
    CompanyType companyType;

    @OneToOne
    @JoinColumn(name = "COMPANY_LOCATION_ID")
    @NotNull
    @Setter
    CompanyLocation companyLocation;

    @Builder
    public Member(Long memberId, String name, String password, String sex, String companyName, CompanyType companyType, CompanyLocation companyLocation) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyLocation = companyLocation;
    }
}
