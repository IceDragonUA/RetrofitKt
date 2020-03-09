package com.evaluation.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchList {

    @SerializedName("results")
    private List<SearchResult> searchResultList;

    public SearchList() {
    }

    public List<SearchResult> getSearchList() {
        return searchResultList;
    }
}
