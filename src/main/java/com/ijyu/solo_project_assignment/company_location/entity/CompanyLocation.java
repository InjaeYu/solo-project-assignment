package com.ijyu.solo_project_assignment.company_location.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class CompanyLocation {
    @Id
    private Long companyLocationId;

    @Getter
    @Column
    @NotNull
    private String companyLocationName;

    @Builder
    public CompanyLocation(Long companyLocationId, String companyLocationName) {
        this.companyLocationId = companyLocationId;
        this.companyLocationName = companyLocationName;
    }
}
