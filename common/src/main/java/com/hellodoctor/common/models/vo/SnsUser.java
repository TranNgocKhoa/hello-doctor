package com.hellodoctor.common.models.vo;

import com.hellodoctor.common.models.UserType;

public class SnsUser {

    private String token;

    private UserType type;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnsUser snsUser = (SnsUser) o;

        return token != null ? token.equals(snsUser.token) : snsUser.token == null;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }
}
