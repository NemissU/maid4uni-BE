package com.swp391.maid4uni.enums;


/**
 * The enum Role.
 */
public enum Role {


    /**
     * Customer role.
     */
    CUSTOMER("0"),
    /**
     * Staff role.
     */
    STAFF("1"),
    /**
     * Manager role.
     */
    MANAGER("2"),
    /**
     * Admin role.
     */
    ADMIN("3");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
