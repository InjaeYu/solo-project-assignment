package com.ijyu.solo_project_assignment.company_type.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class CompanyType {
    @Id
    private Long companyTypeId;

    @Getter
    @Column
    @NotNull
    private String companyTypeName;

    @Builder
    public CompanyType(Long companyTypeId, String companyTypeName) {
        this.companyTypeId = companyTypeId;
        this.companyTypeName = companyTypeName;
    }
}
