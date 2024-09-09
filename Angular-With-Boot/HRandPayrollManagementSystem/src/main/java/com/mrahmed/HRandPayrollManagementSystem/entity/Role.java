package com.mrahmed.HRandPayrollManagementSystem.entity;

import jakarta.persistence.Table;


@Table(name = "roles")
public enum Role {
    ADMIN("ADMIN"),
    HR("HR"),
    MANAGER("MANAGER"),
    PAYROLL("PAYROLL"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static Role fromValue(String value) {
        for (Role role : values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new RuntimeException("Invalid role value: " + value);
    }

}
