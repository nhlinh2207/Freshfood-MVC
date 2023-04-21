package com.linh.utils.enums;

public enum SystemUsers {

    ADMIN("linh0001");
    public String username;

    SystemUsers(String username) {
        this.username = username;
    }

    public String getUsername(){return username;}
}
