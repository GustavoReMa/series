package com.example.series.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.series.R;
import com.example.series.adapter.SerieDetailAdapter;
import com.example.series.interfaces.ISerie;
import com.example.series.model.entity.Serie;
import com.example.series.presenter.SeriePresenter;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerieSearchActivity extends AppCompatActivity implements ISerie.view {

    private String TAG = "SerieSearchActivity";
    private List<Serie> mDataSeries = new ArrayList<>();
    private String token = "";
    private String notFound = "";

    @BindView(R.id.edt_search_series)
    EditText edtSearchSeries;
    @BindView(R.id.btn_search_series)
    ImageView btnSeriesSearch;
    @BindView(R.id.btn_delete_text)
    ImageView btnDeleteText;
    @BindView(R.id.txt_series_notFound)
    TextView txtSeriesNotFound;
    @BindView(R.id.recycler_view_series)
    RecyclerView recyclerView;
    @BindView(R.id.rotateloading_series)
    RotateLoading rotateLoading;
    SerieDetailAdapter serieDetailAdapter;
    ISerie.presenter iSeriePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        iSeriePresenter = new SeriePresenter(this, getApplicationContext());

        edtSearchSeries.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 49) {
                    Toast.makeText(getApplicationContext(), "No puedes escribir más de 50 carácteres", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    btnDeleteText.setVisibility(View.GONE);
                    btnSeriesSearch.setVisibility(View.VISIBLE);
                } else {
                    btnDeleteText.setVisibility(View.GONE);
                    btnSeriesSearch.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @OnClick(R.id.btn_search_series)
    public void searchSeries() {
        String nameSerie = edtSearchSeries.getText().toString();
        if (nameSerie.isEmpty()) {
            Toast.makeText(this, R.string.enter_text, Toast.LENGTH_SHORT).show();
        } else {
            activateRotateloading();
            mDataSeries.clear();
            btnSeriesSearch.setVisibility(View.GONE);
            btnDeleteText.setVisibility(View.VISIBLE);
            txtSeriesNotFound.setText("");
            txtSeriesNotFound.setVisibility(View.GONE);
            getSeries(nameSerie);
            desactivateRotateloading();

        }
    }

    @Override
    public void showErrorApi(String error) {
        desactivateRotateloading();
        Log.e(TAG, "error: " + error);

    }

    @Override
    public void getSeries(String nameSerie) {
        iSeriePresenter.getSeriesApi(nameSerie);
    }

    @Override
    public void showSeries(List<Serie> mDataSeries) {
        this.mDataSeries = mDataSeries;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        serieDetailAdapter = new SerieDetailAdapter(getApplicationContext(), this.mDataSeries);
        recyclerView.setAdapter(serieDetailAdapter);
        serieDetailAdapter.notifyDataSetChanged();
    }

    private void activateRotateloading() {
        rotateLoading.setVisibility(View.VISIBLE);
        rotateLoading.start();
    }

    private void desactivateRotateloading() {
        rotateLoading.setVisibility(View.GONE);
        rotateLoading.stop();
    }

    @OnClick(R.id.btn_delete_text)
    public void deleteText() {
        btnSeriesSearch.setVisibility(View.VISIBLE);
        btnDeleteText.setVisibility(View.GONE);
        txtSeriesNotFound.setText("");
        txtSeriesNotFound.setVisibility(View.GONE);
        edtSearchSeries.setText("");
        mDataSeries.clear();
        serieDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
