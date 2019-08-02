package com.example.series.api;

import com.example.series.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static IUserService createUserService() {
        return createRetrofit().create(IUserService.class);
    }

    private static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
