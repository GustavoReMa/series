package com.example.series.api.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("apikey")
    public String apikey;

    @SerializedName("userkey")
    public String userkey;

    @SerializedName("username")
    public String username;

    public LoginData(String apikey, String userkey, String username) {
        this.apikey = apikey;
        this.userkey = userkey;
        this.username = username;
    }
}
