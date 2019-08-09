package com.example.series.interfaces;

import com.example.series.model.entity.ActorData;

public interface IActorSerie {
    interface model{
        //Interactor
        void getActorSerieApi(int id);
    }

    interface presenter{
        //View
        void showErrorActor(String error);
        //Interactor
        void getActorSerieApi(int id);
        //View
        void showActorSerie(ActorData actorData);
    }

    interface view{
        //View
        void showErrorActor(String error);
        //Interactor
        void getActorSerie(int id);
        //View
        void showActorSerie(ActorData actorData);
    }

}
