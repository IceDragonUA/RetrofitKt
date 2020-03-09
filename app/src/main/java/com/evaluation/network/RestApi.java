package com.evaluation.network;

import com.evaluation.model.asset.Asset;
import com.evaluation.model.search.SearchList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {

    @GET("search/movie?api_key=f7262134aadc3f618b7c4bfac62b4194&language=en-US&query=h&page=1&include_adult=false")
    Call<SearchList> getSearchData();

    @GET("movie/{assetId}?api_key=f7262134aadc3f618b7c4bfac62b4194&language=en-US")
    Call<Asset> getAssetById(@Path("assetId") int assetId);
}


