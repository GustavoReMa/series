package com.example.series.api;

import com.example.series.api.data.LoginData;
import com.example.series.api.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {

    @POST("login")
    Call<User> login(@Body LoginData loginData);
}
