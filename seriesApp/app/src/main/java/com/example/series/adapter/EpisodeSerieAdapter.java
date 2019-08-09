package com.example.series.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.series.R;
import com.example.series.model.entity.Episode;
import com.example.series.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeSerieAdapter extends RecyclerView.Adapter<EpisodeSerieAdapter.ViewHolder> {

    private static final String TAG = "EpisodeSerieAdapter";
    private List<Episode> data;
    private Context ctx;
    private String URL = Constants.URL_BANNER;
    private String URL_COMPLETE;
    private String overViewMore;

    public EpisodeSerieAdapter(List<Episode> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.card_episodes_serie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        Episode episode = data.get(i);

        if (episode.getFilename() != null && !episode.getFilename().equals("")) {
            URL_COMPLETE = URL + episode.getFilename();
            Picasso.get().load(URL_COMPLETE).into(vh.imageEpisode);
        }

        if(episode.getEpisodeName()==null){

        }
        vh.chapterEpisode.setText(episode.getEpisodeName()==null || episode.getEpisodeName().isEmpty() ? "No disponible" : episode.getEpisodeName());

        if(episode.getOverview()==null || episode.getOverview().isEmpty()){
            vh.overviewEpisode.setText(Resources.getSystem().getString(R.string.not_found));
        }else{
            if(episode.getOverview().length()>150){
                overViewMore = episode.getOverview().substring(0,150) + "... Seguir leyendo";
                vh.overviewEpisode.setText(overViewMore);
                vh.overviewEpisode.setOnClickListener(view ->  {
                    Toast.makeText(ctx,"Dio click",Toast.LENGTH_SHORT).show();
                });
            }else{
                vh.overviewEpisode.setText(episode.getOverview());
            }

        }

        vh.firstAiredEpisode.setText(episode.getFirstAired());
        vh.ratingEpisode.setText(episode.getSiteRating());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_episode_serie)
        ImageView imageEpisode;
        @BindView(R.id.chapter_episode_serie)
        TextView chapterEpisode;
        @BindView(R.id.overview_episode_serie)
        TextView overviewEpisode;
        @BindView(R.id.firstAired_episode_serie)
        TextView firstAiredEpisode;
        @BindView(R.id.rating_episode_serie)
        TextView ratingEpisode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
