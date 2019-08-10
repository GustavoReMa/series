package com.example.series.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.model.entity.Actor;
import com.example.series.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorSerieAdapter extends RecyclerView.Adapter<ActorSerieAdapter.ViewHolder> {

    private static final String TAG = "ActorSerieAdapter";
    private List<Actor> data;
    private Context ctx;
    private String URL = Constants.URL_BANNER;
    private String URL_COMPLETE;

    public ActorSerieAdapter(List<Actor> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.card_actors_serie, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        Actor actor = data.get(i);
        if (actor.getImage() != null && !actor.getImage().equals("")) {
            URL_COMPLETE = URL + actor.getImage();
            Picasso.get().load(URL_COMPLETE).into(vh.imageActor);
        }

        vh.nameActor.setText(actor.getName());
        vh.roleActor.setText(actor.getRole());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_actor_serie)
        ImageView imageActor;
        @BindView(R.id.name_actor_serie)
        TextView nameActor;
        @BindView(R.id.role_actor_serie)
        TextView roleActor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
