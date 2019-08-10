package com.example.series.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.series.api.IUserService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.ILogin;
import com.example.series.model.entity.LoginRequest;
import com.example.series.model.entity.User;
import com.example.series.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements ILogin.model {

    private static final String TAG = "LoginInteractor";
    private ILogin.presenter iLoginPresenter;
    private Context ctx;

    public LoginInteractor(ILogin.presenter iLoginPresenter, Context ctx) {
        this.iLoginPresenter = iLoginPresenter;
        this.ctx = ctx;
    }


    @Override
    public void getTokenApi() {
        IUserService userService = ServiceClient.createUserService();
        Call<User> userCall = userService.login(new LoginRequest("PPDZ39EGKOEHNR3R",
                "JOEZYXMFGR0RDBXA", "tavromero2yu"));

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                final User user = response.body();
                if (response.code() == 200) {
                    assert user != null;
                    Log.e(TAG, ">>>>Response login>>>>" + user.getToken());
                    SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.NAME_PREFS, Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString(Constants.PREFS_KEY_TOKEN, user.getToken()).apply();
                    iLoginPresenter.showTokenUser(user.getToken());
                } else {
                    if (response.code() == 401) {
                        iLoginPresenter.showErrorApi("Not authorized");

                    } else {
                        if (response.code() == 404) {
                            iLoginPresenter.showErrorApi("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
