package com.evaluation.model.asset;

import com.google.gson.annotations.SerializedName;

public class Asset {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String posterPath;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
