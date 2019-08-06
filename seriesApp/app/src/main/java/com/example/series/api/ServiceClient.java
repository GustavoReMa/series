package com.example.series.api;

import com.example.series.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    public static IUserService createUserService() {
        return createRetrofit().create(IUserService.class);
    }

    public static ISerieService createSerieService() {
        return createRetrofit().create(ISerieService.class);
    }

    private static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
