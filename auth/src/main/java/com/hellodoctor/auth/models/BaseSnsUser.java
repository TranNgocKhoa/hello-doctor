package com.hellodoctor.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseSnsUser {

    @JsonIgnore
    private long userRefId;

    public abstract String getId();

    public abstract void setId(String id);

    public long getUserRefId() {
        return userRefId;
    }

    public void setUserRefId(long userRefId) {
        this.userRefId = userRefId;
    }
}
