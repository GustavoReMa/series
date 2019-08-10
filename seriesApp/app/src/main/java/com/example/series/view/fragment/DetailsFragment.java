package com.example.series.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.series.R;
import com.example.series.model.entity.SerieDetail;
import com.example.series.model.entity.SerieDetailExtras;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private static final String TAG = "DetailsFragment";
    private SerieDetail serieDetails;
    private SerieDetailExtras serieDetailExtras;
    private TextView txtGenreDescription, txtFirstAiredDescription, txtAirsTimeDescription,
            txtAirsDay, txtOverviewDescription, txtRatingDescription, txtTotalSeasons;
    private ImageView imgPoster;
    private Bundle args;
    private String imdbRating = "", noDescription = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {

            String streamDetails = (String) args.getSerializable("details");
            serieDetails = new Gson().fromJson(streamDetails, SerieDetail.class);

            if (args.getSerializable("description") == null) {
                noDescription = "No disponible";
            } else {
                String streamDescription = (String) args.getSerializable("description");
                serieDetailExtras = new Gson().fromJson(streamDescription, SerieDetailExtras.class);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        imgPoster = view.findViewById(R.id.poster_detail_serie);
        txtGenreDescription = view.findViewById(R.id.genre_description_details_serie);
        txtFirstAiredDescription = view.findViewById(R.id.aired_description_details_serie);
        txtAirsTimeDescription = view.findViewById(R.id.airstime_description_details_serie);
        txtAirsDay = view.findViewById(R.id.airsday_description_details_serie);
        txtTotalSeasons = view.findViewById(R.id.total_seasons_description_details_serie);
        txtOverviewDescription = view.findViewById(R.id.overview_description_details_serie);
        txtRatingDescription = view.findViewById(R.id.rating_description_details_serie);
        clearView();

        if (args != null) {
            if (serieDetailExtras == null) {
                txtGenreDescription.setText(noDescription);
                txtTotalSeasons.setText(noDescription);
                txtRatingDescription.setText(noDescription);
            } else {
                Picasso.get().load(serieDetailExtras.getPoster()).into(imgPoster);




                txtGenreDescription.setText(serieDetailExtras.getGenre());
                txtTotalSeasons.setText(serieDetailExtras.getTotalSeasons());
                imdbRating = serieDetailExtras.getImdbRating() + getString(R.string.divider_rating);
                txtRatingDescription.setText(imdbRating);
            }

            txtFirstAiredDescription.setText(serieDetails.getFirstAired() != null && !serieDetails.getFirstAired().isEmpty() ? serieDetails.getFirstAired() : "No disponible");
            if (serieDetails.getAirsTime() != null && !serieDetails.getAirsTime().isEmpty()) {
                txtAirsTimeDescription.setText(serieDetails.getAirsTime());
                txtAirsDay.setText(serieDetails.getAirsDayOfWeek());
            } else {
                txtAirsTimeDescription.setText(serieDetails.getAirsTime() != null && !serieDetails.getAirsTime().isEmpty() ? serieDetails.getAirsTime() : "No disponible");
            }
            txtOverviewDescription.setText(serieDetails.getOverview() != null && !serieDetails.getOverview().isEmpty() ? serieDetails.getOverview() : "No disponible");

        }
        return view;
    }

    void clearView() {
        txtGenreDescription.setText("");
        txtFirstAiredDescription.setText("");
        txtAirsTimeDescription.setText("");
        txtAirsDay.setText("");
        txtTotalSeasons.setText("");
        txtOverviewDescription.setText("");
        txtRatingDescription.setText("");
    }
}
