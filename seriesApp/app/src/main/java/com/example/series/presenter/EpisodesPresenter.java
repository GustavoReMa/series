package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.IEpisodesSerie;
import com.example.series.model.EpisodesInteractor;
import com.example.series.model.entity.EpisodeData;

public class EpisodesPresenter implements IEpisodesSerie.presenter {

    private IEpisodesSerie.view iEpisodesSerieView;
    private IEpisodesSerie.model iEpisodesSerieInteractor;
    private Context ctx;

    public EpisodesPresenter(IEpisodesSerie.view iEpisodesSerieView, Context ctx) {
        this.iEpisodesSerieView = iEpisodesSerieView;
        this.ctx = ctx;
        iEpisodesSerieInteractor = new EpisodesInteractor(this, this.ctx);
    }

    @Override
    public void showErrorEpisodes(String error) {
        iEpisodesSerieView.showErrorEpisodes(error);
    }

    @Override
    public void getEpisodesSerieApi(int id, int season) {
        iEpisodesSerieInteractor.getEpisodesSerieApi(id, season);
    }

    @Override
    public void showEpisodesSerie(EpisodeData episodeData) {
        iEpisodesSerieView.showEpisodesSerie(episodeData);
    }
}
