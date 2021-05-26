package com.example.pbe_table.ui.main.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String ip;
    public LoggedInUser(String userId, String displayName, String ip) {
        this.ip=ip;
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIp() {
        return ip;
    }

}