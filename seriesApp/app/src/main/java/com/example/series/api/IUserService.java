package com.example.series.api;

import com.example.series.model.entity.LoginRequest;
import com.example.series.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {

    @POST("login")
    Call<User> login(@Body LoginRequest loginRequest);
}
