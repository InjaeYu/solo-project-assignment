package com.ijyu.solo_project_assignment.exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User exists"),
    COMPANY_NAME_NOT_FOUND(404, "Company name not found"),
    COMPANY_NAME_EXISTS(409, "Company name exists"),
    COMPANY_LOCATION_NOT_FOUND(404, "Company location not found"),
    COMPANY_LOCATION_EXISTS(409, "Company location exists"),
    COMPANY_TYPE_NOT_FOUND(404, "Company type not found"),
    COMPANY_TYPE_EXISTS(409, "Company type exists"),
    FILTER_NOT_FOUND(404, "Filter not found");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
