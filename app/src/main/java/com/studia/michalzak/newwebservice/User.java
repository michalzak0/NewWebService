package com.studia.michalzak.newwebservice;

import java.util.Map;

public class User {
    private String userId;
    private String name;
    private String Message;

    public User(String userId, String name, String message) {
        this.userId = userId;
        this.name = name;
        Message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return Message;
    }

    public Map<String, String> toJson{

    }
}
