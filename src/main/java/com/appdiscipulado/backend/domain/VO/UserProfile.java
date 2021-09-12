package com.appdiscipulado.backend.domain.VO;

public enum UserProfile {
    LEADER("L"),
    CO_LEADER("CL"),
    ADMINISTRATOR("ADM"),
    MANAGER("M");

    private String userProfileIdentifier;

    private UserProfile(String userProfileIdentifier) {
        this.userProfileIdentifier = userProfileIdentifier;
    }

    public String getUserProfileIdentifier() {
        return this.userProfileIdentifier;
    }
}
