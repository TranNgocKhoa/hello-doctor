package com.hellodoctor.auth.models;

import com.hellodoctor.common.models.UserType;

public class SnsParameter {
    private long userId;
    private String displayName;
    private long snsId;
    private String snsType;

    public SnsParameter() {
    }

    public SnsParameter(long snsId, String displayName, UserType type) {
        this.snsId = snsId;
        this.displayName = displayName;
        this.snsType = type.name();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getSnsId() {
        return snsId;
    }

    public void setSnsId(long snsId) {
        this.snsId = snsId;
    }

    public String getSnsType() {
        return snsType;
    }

    public void setSnsType(String snsType) {
        this.snsType = snsType;
    }
}
