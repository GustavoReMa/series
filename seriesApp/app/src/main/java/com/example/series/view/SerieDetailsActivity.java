package com.example.series.view;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.api.ISerieService;
import com.example.series.api.ServiceClient;
import com.example.series.interfaces.ISerieDetail;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;
import com.example.series.presenter.SerieDetailsPresenter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerieDetailsActivity extends AppCompatActivity implements ISerieDetail.view {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.name_serie_tab1)
    TextView seriesName;
    @BindView(R.id.melon_left_toolbar)
    ImageView melonLeft;
    @BindView(R.id.melon_right_toolbar)
    ImageView melonRight;

    private static final String TAG = "SerieDetailsActivity";
    Boolean loading = false;
    Bundle args;
    private ISerieDetail.presenter iSerieDetailPresenter;

    private FragmentTabHost tabHost;
    private String token = "", titleSerie = "",imdId = "";
    private SerieDetail serieDetail;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iSerieDetailPresenter = new SerieDetailsPresenter(this,getApplicationContext());
        Intent intent = getIntent();
        args = intent.getBundleExtra("serie");

        //seriesName = findViewById(R.id.name_serie_tab1);

        btnBack.setVisibility(View.VISIBLE);
        melonLeft.setVisibility(View.GONE);
        melonRight.setVisibility(View.VISIBLE);

        this.id = args.getInt("id");
        Log.e(TAG,">>>>>>>>Id recuperado>>>>>>>" + id);
        titleSerie = args.getString("seriesName");
        seriesName.setText(titleSerie);

        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Detalles"), DetailsFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Episodios"), EpisodesFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Actores"), ActorsFragment.class, null);

        navigateToFragmentDetails(this.id);

        tabHost.setOnTabChangedListener(s -> {
            switch (s) {
                case "tab1":
                    navigateToFragmentDetails(this.id);
                    break;
                case "tab2":
                    //navigateToFragmentEpisodes(this.token, this.id, 1);
                    break;
                case "tab3":
                    //navigateToFragmentActors(this.token, this.id);
                    break;
            }

        });
    }

    private void navigateToFragmentDetails(int id) {
        getSerieDetail(id);
    }

    @Override
    public void showError(String error) {
        Log.e(TAG,"error: " + error);
    }

    @Override
    public void getSerieDetail(int id) {
        iSerieDetailPresenter.getDetailsSerieApi(id);
    }

    @Override
    public void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras) {
        Gson gson = new Gson();
        //args = null;
        args.putSerializable("details",gson.toJson(serieDetail));
        if(serieDetailExtras==null){
            args.putSerializable("description",null);
        }else{
            args.putSerializable("description",gson.toJson(serieDetailExtras));
        }

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_details_series,detailsFragment);
        fragmentTransaction.commit();

    }
    @OnClick(R.id.btn_back)
    public void btnBack() {
        finish();
    }
}
