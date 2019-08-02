package com.example.series.model;

import android.util.Log;

import com.example.series.api.IUserService;
import com.example.series.api.ServiceFactory;
import com.example.series.api.data.LoginData;
import com.example.series.api.data.User;
import com.example.series.presenter.ILoginPresenter;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository implements ILoginRepository {
    private static final String TAG = "LoginRepository";
    ILoginPresenter iLoginPresenter;

    public LoginRepository(ILoginPresenter iLoginPresenter){
        this.iLoginPresenter = iLoginPresenter;
    }

    //Implementar lógica de conexión
    @Override
    public void getTokenAPI() {
        //iLoginPresenter.showToken(response.body());
        IUserService userService = ServiceFactory.createUserService();
        Call<User> userCall = userService.login(new LoginData("PPDZ39EGKOEHNR3R",
                "JOEZYXMFGR0RDBXA", "tavromero2yu"));

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                final User user = response.body();
                if(response.code()==200){
                    Gson gson = new Gson();
                    assert user != null;
                    Log.e(TAG,"Response token: " + user.getToken());
                    iLoginPresenter.showToken(user.getToken());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
