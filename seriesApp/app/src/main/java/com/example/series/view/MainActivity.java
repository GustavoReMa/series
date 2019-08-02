package com.example.series.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.series.R;
import com.example.series.presenter.ILoginPresenter;
import com.example.series.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements ILoginView{

    private static final String TAG = "MainActivity";
    ILoginPresenter iLoginPresenter;
    TextView txtHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iLoginPresenter = new LoginPresenter(this);
        txtHelloWorld = findViewById(R.id.txtHelloWorld);

        getToken();

    }

    @Override
    public void showToken(String token) {
        Toast.makeText(this,token,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getToken() {
        iLoginPresenter.getToken();
    }
}
