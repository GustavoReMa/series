package com.example.series.model.entity;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("apikey")
    public String apikey;

    @SerializedName("userkey")
    public String userkey;

    @SerializedName("username")
    public String username;

    public LoginRequest(String apikey, String userkey, String username) {
        this.apikey = apikey;
        this.userkey = userkey;
        this.username = username;
    }
}
