package com.example.series.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.model.entity.NumberSeason;
import com.example.series.utils.IListenerClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberSeasonAdapter extends RecyclerView.Adapter<NumberSeasonAdapter.ViewHolder> {

    private static final String TAG = "NumberSeasonAdaper";
    private List<NumberSeason> data;
    private Context ctx;
    private IListenerClick IListenerClick;

    public NumberSeasonAdapter(List<NumberSeason> data, Context ctx, IListenerClick IListenerClick) {
        this.data = data;
        this.ctx = ctx;
        this.IListenerClick = IListenerClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.card_serie_seasons, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
        NumberSeason numberSeason = data.get(i);
        String number = "" + numberSeason.getNumberSeason();
        vh.numberSeason.setText(number);

        vh.itemView.setOnClickListener(view -> {
            if (IListenerClick != null) {
                IListenerClick.getPositionClicked(i + 1);
            }
        });

        if (numberSeason.isSelected()) {
            vh.itemView.setBackgroundColor(Color.rgb(255, 87, 34));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.number_season_episodes)
        TextView numberSeason;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
