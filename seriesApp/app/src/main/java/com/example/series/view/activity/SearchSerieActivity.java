package com.example.series.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.series.R;
import com.example.series.adapter.DetailSerieAdapter;
import com.example.series.interfaces.ISerie;
import com.example.series.model.entity.Serie;
import com.example.series.presenter.SeriePresenter;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchSerieActivity extends AppCompatActivity implements ISerie.view {

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
    private DetailSerieAdapter detailSerieAdapter;
    private ISerie.presenter iSeriePresenter;
    private String TAG = "SearchSerieActivity";
    private List<Serie> mDataSeries = new ArrayList<>();
    private String token = "";
    private String notFound = "";
    private String nameSerie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_search);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        nameSerie = edtSearchSeries.getText().toString();
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
        }
    }

    @Override
    public void showError(String error) {
        desactivateRotateloading();
        notFound = getString(R.string.not_found) + " " + nameSerie;
        txtSeriesNotFound.setVisibility(View.VISIBLE);
        txtSeriesNotFound.setText(notFound);

    }

    @Override
    public void getSeries(String nameSerie) {
        iSeriePresenter.getSeriesApi(nameSerie);
    }

    @Override
    public void showSeries(List<Serie> mDataSeries) {
        desactivateRotateloading();
        this.mDataSeries = mDataSeries;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detailSerieAdapter = new DetailSerieAdapter(getApplicationContext(), this.mDataSeries);
        recyclerView.setAdapter(detailSerieAdapter);
        detailSerieAdapter.notifyDataSetChanged();
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
        detailSerieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);
    }
}
