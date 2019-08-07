package com.example.series.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.series.R;
import com.example.series.interfaces.ILogin;
import com.example.series.presenter.LoginPresenter;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILogin.view {

    private static final String TAG = "LoginActivity";
    ILogin.presenter iLoginPresenter;
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.rotateloading_login)
    RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        iLoginPresenter = new LoginPresenter(this, getApplicationContext());
    }

    @OnClick(R.id.btn_login)
    public void login() {
        rotateLoading.start();
        getTokenUser();
    }


    @Override
    public void getTokenUser() {
        iLoginPresenter.getTokenApi();
    }

    @Override
    public void showErrorApi(String error) {
        Log.e(TAG, "Error " + error);
    }

    @Override
    public void showTokenUser(String token) {
        rotateLoading.stop();
        Log.e(TAG,token);
        Intent intent = new Intent(LoginActivity.this, SerieSearchActivity.class);
        startActivity(intent);
    }
}
