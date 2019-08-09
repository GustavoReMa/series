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
import com.example.series.interfaces.IDetailSerie;
import com.example.series.interfaces.IEpisodesSerie;
import com.example.series.model.entity.Episode;
import com.example.series.model.entity.EpisodeData;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;
import com.example.series.presenter.EpisodesPresenter;
import com.example.series.presenter.SerieDetailsPresenter;
import com.example.series.utils.IListenerClick;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerieDetailsActivity extends AppCompatActivity implements IDetailSerie.view, IListenerClick, IEpisodesSerie.view {

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
    private IDetailSerie.presenter iSerieDetailPresenter;
    private IEpisodesSerie.presenter iSerieEpisodesPresenter;

    private FragmentTabHost tabHost;
    private String token = "", titleSerie = "",imdId = "";
    private SerieDetail serieDetail;
    private int id = 0;
    private int numberSeason,season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iSerieDetailPresenter = new SerieDetailsPresenter(this,getApplicationContext());
        iSerieEpisodesPresenter = new EpisodesPresenter(this,getApplicationContext());
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
                    navigateToFragmentEpisodes(this.id, 1);
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

    private void navigateToFragmentEpisodes(int id, int season) {
        this.season = season;
        getEpisodesSerieApi(id,season);
    }

    //>>>>>>>>>>>>Detalles de serie<<<<<<<<<<<<<<<<<//
    @Override
    public void showErrorDetails(String error) {
        Log.e(TAG,"error: " + error);
    }

    @Override
    public void getSerieDetail(int id) {
        iSerieDetailPresenter.getDetailsSerieApi(id);
    }

    @Override
    public void showDetailsSerie(SerieDetail serieDetail, SerieDetailExtras serieDetailExtras) {
        Gson gson = new Gson();
        args.putSerializable("details",gson.toJson(serieDetail));
        if(serieDetailExtras==null){
            args.putSerializable("description",null);
        }else{
            args.putSerializable("description",gson.toJson(serieDetailExtras));
            if(serieDetailExtras.getTotalSeasons().equals("N/A") || serieDetailExtras.getTotalSeasons() == null){
                numberSeason = 0;
            }else{
                numberSeason = Integer.parseInt(serieDetailExtras.getTotalSeasons());
            }
        }

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_details_series,detailsFragment);
        fragmentTransaction.commit();

    }
    //>>>>>>>>>>>>Termina detalles de serie<<<<<<<<<<<<<<<<<//

    //>>>>>>>>>>>>Episodios de serie<<<<<<<<<<<<<<<<<//
    @Override
    public void showErrorEpisodes(String error) {
        args.putSerializable("notFound","notFound");
        EpisodesFragment episodesFragment = new EpisodesFragment();
        episodesFragment.setArguments(args);
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.layout_episodes_series, episodesFragment);
        transaction2.commit();
        Log.e(TAG,"Error: " + error);
    }

    @Override
    public void getEpisodesSerieApi(int id, int season) {
        iSerieEpisodesPresenter.getEpisodesSerieApi(id,season);
    }

    @Override
    public void showEpisodesSerie(EpisodeData episodeData) {
        Gson gson = new Gson();
        args.putSerializable("episodes",gson.toJson(episodeData));
        args.putInt("numberSeason",numberSeason);
        args.putInt("position",season);
        args.remove("notFound");
        EpisodesFragment episodesFragment = new EpisodesFragment();
        episodesFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_episodes_series,episodesFragment);
        fragmentTransaction.commit();

    }

    //>>>>>>>>>>>>Termina episodios de serie<<<<<<<<<<<<<<<<<//

    @OnClick(R.id.btn_back)
    public void btnBack() {
        finish();
    }

    @Override
    public void getPositionClicked(int pos) {
        //Método para saber en qué posición se dió click para ver la temporada2
        Log.e(TAG,"Id de getPositionClicked " + this.id + " y posicion " + pos);
        navigateToFragmentEpisodes(this.id,pos);
    }
}
