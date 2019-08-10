package com.example.series.interfaces;

import com.example.series.model.entity.EpisodeData;

public interface IEpisodesSerie {

    interface model {
        void getEpisodesSerieApi(int id, int season);
    }

    interface presenter {
        //View
        void showErrorEpisodes(String error);

        //Interactor
        void getEpisodesSerieApi(int id, int season);

        //View
        void showEpisodesSerie(EpisodeData episodeData);

    }

    interface view {
        //View
        void showErrorEpisodes(String error);

        //Presenter
        void getEpisodesSerieApi(int id, int season);

        //View
        void showEpisodesSerie(EpisodeData episodeData);
    }

}
