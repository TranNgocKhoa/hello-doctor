package com.hellodoctor.common.models.vo;

public class UserVo {

    private String host;
    private String username;
    private String password;

    private String snsToken;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSnsToken() {
        return snsToken;
    }

    public void setSnsToken(String snsToken) {
        this.snsToken = snsToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserVo userVo = (UserVo) o;

        if (host != null ? !host.equals(userVo.host) : userVo.host != null) return false;
        if (username != null ? !username.equals(userVo.username) : userVo.username != null) return false;
        return password != null ? password.equals(userVo.password) : userVo.password == null;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
