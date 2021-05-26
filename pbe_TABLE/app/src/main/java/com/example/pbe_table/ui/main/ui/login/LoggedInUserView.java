package com.example.pbe_table.ui.main.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String ip;
    private String id;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String ip, String id) {
        this.displayName = displayName;
        this.ip=ip;
        this.id= id;
    }
    String getId(){
        return id;
    }
    String getDisplayName() {
        return displayName;
    }

    String getIp(){
        return  ip;
    }
}