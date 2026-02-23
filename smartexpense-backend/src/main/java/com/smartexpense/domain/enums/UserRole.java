package com.smartexpense.domain.enums;

public enum UserRole {
    INTERN,
    ANALYST,
    MANAGER;

    public static UserRole getDefault(){
        return INTERN;
    }
}
