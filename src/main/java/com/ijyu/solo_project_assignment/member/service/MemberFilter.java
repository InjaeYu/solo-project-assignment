package com.ijyu.solo_project_assignment.member.service;

import lombok.Getter;

@Getter
public enum MemberFilter {
    NONE("None"),
    TYPE("Type"),
    LOCATION("Location");

    private final String filter;

    MemberFilter(String filter) {
        this.filter = filter;
    }
}
