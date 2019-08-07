package com.example.series.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.model.entity.Serie;
import com.example.series.utils.Constants;
import com.example.series.view.SerieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerieDetailAdapter extends RecyclerView.Adapter<SerieDetailAdapter.SeriesViewHolder> {
    String TAG = "SerieDetailsAdapter";
    private List<Serie> data;
    private Context ctx;

    public SerieDetailAdapter(Context ctx, List<Serie> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.card_image_serie, viewGroup, false);

        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int i) {
        Serie serie = data.get(i);
        holder.title.setText(serie.getSeriesName());
        if (!serie.getBanner().isEmpty()) {
            String URL = Constants.URL_BANNER + serie.getBanner();
            Log.d(TAG, "url: " + URL);
            Picasso.get().load(URL).into(holder.banner);
            holder.banner.setBackgroundColor(Color.WHITE);
        }

        holder.banner.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(ctx, SerieDetailsActivity.class);
            bundle.putInt("id", serie.getId());
            bundle.putString("seriesName", serie.getSeriesName());
            intent.putExtra("serie", bundle);

            ctx.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class SeriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_serie)
        TextView title;
        @BindView(R.id.banner_serie)
        ImageView banner;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
