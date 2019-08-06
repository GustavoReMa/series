package com.example.series.interfaces;

import com.example.series.model.entity.Serie;

import java.util.List;

public interface ISerie {

    interface model {
        //Interactor
        void getSeriesApi(String nameSerie);
    }

    interface presenter {
        //Presenter
        void showErrorApi(String error);

        //Interactor
        void getSeriesApi(String nameSerie);

        //View
        void showSeries(List<Serie> series);
    }

    interface view {
        //Presenter
        void showErrorApi(String error);

        //Presenter
        void getSeries(String nameSerie);

        //View
        void showSeries(List<Serie> series);
    }
}
