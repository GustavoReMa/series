package com.example.series.api.data;

public class User {

    private String token;

    public User(String name, String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
