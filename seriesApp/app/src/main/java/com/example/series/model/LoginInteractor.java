package com.example.series.model;

import com.example.series.presenter.ILoginPresenter;

public class LoginInteractor implements ILoginInteractor {

    private ILoginPresenter iLoginPresenter;
    private ILoginRepository loginRepository;

    public LoginInteractor(ILoginPresenter iLoginPresenter){
        this.iLoginPresenter = iLoginPresenter;
        loginRepository = new LoginRepository(this.iLoginPresenter);
    }

    @Override
    public void getTokenAPI() {
        loginRepository.getTokenAPI();
    }
}
