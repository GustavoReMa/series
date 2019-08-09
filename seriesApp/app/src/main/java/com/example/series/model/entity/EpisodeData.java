package com.example.series.model.entity;

import java.util.List;

public class EpisodeData {
    private List<Episode> data;
    public List<Episode> getData(){
        return data;
    }

    public void setData(List<Episode> data){
        this.data = data;
    }
}
