package com.example.series.presenter;

import android.content.Context;

import com.example.series.interfaces.IActorSerie;
import com.example.series.model.ActorsInteractor;
import com.example.series.model.entity.ActorData;

public class ActorsPresenter implements IActorSerie.presenter {

    IActorSerie.view iActorSerieView;
    IActorSerie.model iActorSerieInteractor;
    Context ctx;

    public ActorsPresenter(IActorSerie.view iActorSerieView, Context ctx){
        this.iActorSerieView = iActorSerieView;
        this.ctx = ctx;
        iActorSerieInteractor = new ActorsInteractor(this,this.ctx);
    }

    @Override
    public void showErrorActor(String error) {
        iActorSerieView.showErrorActor(error);
    }

    @Override
    public void getActorSerieApi(int id) {
        iActorSerieInteractor.getActorSerieApi(id);
    }

    @Override
    public void showActorSerie(ActorData actorData) {
        iActorSerieView.showActorSerie(actorData);
    }
}
