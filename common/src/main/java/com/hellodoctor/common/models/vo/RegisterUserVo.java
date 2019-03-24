package com.hellodoctor.common.models.vo;

public class RegisterUserVo extends UserVo {

    private long userId;

    private String email;


    private String referredBy;


    private SnsUser snsInfo;

//    private UserProfile profile;
//
//    private UserPreference prefs;

    public RegisterUserVo() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferredBy() {
        return this.referredBy == null ? "" : this.referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public SnsUser getSnsInfo() {
        return this.snsInfo;
    }

    public void setSnsInfo(SnsUser snsInfo) {
        this.snsInfo = snsInfo;
    }

//    public UserProfile getProfile() {
//        return this.profile == null ? new UserProfile() : this.profile;
//    }
//
//    public void setProfile(UserProfile profile) {
//        this.profile = profile;
//    }
//
//    public UserPreference getPrefs() {
//        return this.prefs == null ? new UserPreference() : this.prefs;
//    }
//
//    public void setPrefs(UserPreference prefs) {
//        this.prefs = prefs;
//    }
}
