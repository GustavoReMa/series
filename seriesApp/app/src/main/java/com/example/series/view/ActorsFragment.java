package com.example.series.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.adapter.ActorSerieAdapter;
import com.example.series.model.entity.ActorData;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActorsFragment extends Fragment {

    private static final String TAG = "ActorsFragment";
    Bundle args;
    ActorData actorList;
    ActorSerieAdapter mAdapter;
    RecyclerView actorsRecyclerView;
    String notFound = "";
    TextView txtActorsNotFound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {

            if(args.getString("notFound")==null){
                String streamActors = (String) args.getSerializable("actors");
                actorList = new Gson().fromJson(streamActors, ActorData.class);

                mAdapter = new ActorSerieAdapter(actorList.getData(), getContext());
                mAdapter.notifyDataSetChanged();
            }else{
                notFound = "notFound";
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actors, container, false);
        actorsRecyclerView = view.findViewById(R.id.actors_recyclerview);
        txtActorsNotFound = view.findViewById(R.id.txt_actors_notFound);

        if (args != null) {
            if(notFound.equals("notFound")){
                txtActorsNotFound.setVisibility(View.VISIBLE);
            }else{
                actorsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                actorsRecyclerView.setHasFixedSize(true);
                actorsRecyclerView.setAdapter(mAdapter);
            }
        }

        return view;

    }

}
