package com.swp391.maid4uni.enums;

public enum PeriodType {
    ONE_MONTH("1 Tháng"),
    TWO_MONTH("2 Tháng");
    private final String value;

    private PeriodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
