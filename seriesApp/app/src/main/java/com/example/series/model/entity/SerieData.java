package com.example.series.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SerieData {
    @SerializedName("data")
    private List<Serie> data;

    public List<Serie> getData() {
        return data;
    }

    public void setData(List<Serie> data) {
        this.data = data;
    }
}
