package com.example.series.presenter;

import com.example.series.model.ILoginInteractor;
import com.example.series.model.LoginInteractor;
import com.example.series.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView iLoginView;
    ILoginInteractor loginInteractor = new LoginInteractor(this);

    public LoginPresenter(ILoginView iLoginView){
        this.iLoginView = iLoginView;
    }

    @Override
    public void showToken(String token) {
        iLoginView.showToken(token);
    }

    @Override
    public void getToken() {
        loginInteractor.getTokenAPI();
    }

}
