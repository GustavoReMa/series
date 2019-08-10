package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.ILogin;
import com.example.series.model.LoginInteractor;

public class LoginPresenter implements ILogin.presenter {

    private ILogin.view iLoginView;
    private ILogin.model loginInteractor;
    private Context ctx;

    public LoginPresenter(ILogin.view iLoginView, Context ctx) {
        this.iLoginView = iLoginView;
        this.ctx = ctx;

        loginInteractor = new LoginInteractor(this, ctx);
    }

    @Override
    public void getTokenApi() {
        loginInteractor.getTokenApi();
    }


    @Override
    public void showErrorApi(String error) {
        iLoginView.showErrorApi(error);
    }

    @Override
    public void showTokenUser(String token) {
        iLoginView.showTokenUser(token);
    }

}
