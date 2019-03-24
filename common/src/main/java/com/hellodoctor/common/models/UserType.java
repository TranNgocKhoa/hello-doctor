package com.hellodoctor.common.models;

public enum UserType {
    NORMAL,
    GOOGLE("gg"),
    FACEBOOK("fa"),
    OTHER;

    private String name;

    UserType() {
    }

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
