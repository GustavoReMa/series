package com.example.series.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.adapter.EpisodeSerieAdapter;
import com.example.series.adapter.NumberSeasonAdapter;
import com.example.series.model.entity.EpisodeData;
import com.example.series.model.entity.NumberSeason;
import com.example.series.utils.IListenerClick;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EpisodesFragment extends Fragment {

    private static final String TAG = "EpisodesFragment";
    Bundle args;
    String notFound = "";
    int numberSeasons = 0;
    List<NumberSeason> numbersSeasonData;
    NumberSeason numberSeason;
    NumberSeasonAdapter mNumberSeasonAdapter;

    EpisodeData episodeSerieDataList;
    EpisodeSerieAdapter mEpisodeSerieAdapter;
    RecyclerView numberSeasonRecyclerView, episodesRecyclerView;
    IListenerClick IListenerClick;
    TextView txtSeasonsEpisodes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {
            if(args.getString("notFound")==null){
                String streamEpisodes = (String) args.getSerializable("episodes");
                Log.d(TAG, "Recuperado desde bundle episodios " + streamEpisodes);
                episodeSerieDataList = new Gson().fromJson(streamEpisodes, EpisodeData.class);
                numberSeasons = args.getInt("numberSeason");
                int position = args.getInt("position");
                numbersSeasonData = new ArrayList<>();


                for (int i = 0; i < numberSeasons; i++) {
                    if(i==position-1){
                        numberSeason = new NumberSeason(i + 1,true);
                    }else{
                        numberSeason = new NumberSeason(i + 1,false);
                    }
                    numbersSeasonData.add(numberSeason);
                }

                mNumberSeasonAdapter = new NumberSeasonAdapter(numbersSeasonData, getContext(), IListenerClick);
                mNumberSeasonAdapter.notifyDataSetChanged();

                mEpisodeSerieAdapter = new EpisodeSerieAdapter(episodeSerieDataList.getData(), getContext());
                mEpisodeSerieAdapter.notifyDataSetChanged();
            }else{
                notFound = "notFound";
            }

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IListenerClick) {
            IListenerClick = (IListenerClick) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        IListenerClick = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episodes, container, false);
        txtSeasonsEpisodes  = view.findViewById(R.id.txt_seasons_episodes);
        numberSeasonRecyclerView = view.findViewById(R.id.numbers_season_recycler_view);
        episodesRecyclerView = view.findViewById(R.id.episodes_season_recyclerview);

        if(notFound.equals("notFound")){
            txtSeasonsEpisodes.setText("No contiene temporadas");
        }

        if (args != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            numberSeasonRecyclerView.setLayoutManager(layoutManager);
            numberSeasonRecyclerView.setHasFixedSize(true);
            numberSeasonRecyclerView.setAdapter(mNumberSeasonAdapter);

            episodesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            episodesRecyclerView.setHasFixedSize(true);
            episodesRecyclerView.setAdapter(mEpisodeSerieAdapter);

        }
        return view;
    }



}
