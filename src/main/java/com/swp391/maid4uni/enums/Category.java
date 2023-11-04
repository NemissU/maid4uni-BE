package com.swp391.maid4uni.enums;

public enum Category {
    COMBO1("Dọn dẹp nhà cơ bản"),
    COMBO2("Dọn dẹp nhà tiết kiệm"),
    COMBO3("Dọn nhà premium"),
    COMBO4("Dọn nhà chuyên sâu"),
    COMBO5("Dọn khu vực bếp"),
    COMBO6("Dọn nhà tối giản");

    private final String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
