package com.ifsp.bugtracker.data.enums;

public enum IssueStatus {
    New(0), 
    Pending(1),
    InProgress(2),
    Resolved (3),
    Cancelled(4);

    private final int value;

    IssueStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
