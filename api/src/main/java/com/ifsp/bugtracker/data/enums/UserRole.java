package com.ifsp.bugtracker.data.enums;

public enum UserRole {
    ADMIN("admin"),
    ANALYST("analyst"),
    CUSTOMER("customer");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}