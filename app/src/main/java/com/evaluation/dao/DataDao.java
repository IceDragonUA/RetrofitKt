package com.evaluation.dao;

import com.evaluation.model.asset.Asset;
import com.evaluation.model.search.SearchList;
import com.evaluation.model.search.SearchResult;
import com.evaluation.network.IDataResult;
import com.evaluation.network.RestAdapter;
import com.evaluation.network.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDao {

    private static DataDao instance;

    private final RestApi restApi;

    public static DataDao getInstance() {
        if (instance == null) {
            instance = new DataDao();
        }
        return instance;
    }

    private DataDao() {
        restApi = RestAdapter.getInstance();
    }

    public void getSearchList(final IDataResult<List<SearchResult>> searchListCallback) {
        Call<SearchList> call = restApi.getSearchData();
        call.enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                if (response.isSuccessful()) {
                    SearchList searchList = response.body();
                    searchListCallback.onResult(searchList.getSearchList());
                }
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
            }
        });
    }

    public void getAssetById(final int assetId, final IDataResult<Asset> assetListCallback) {
        Call<Asset> call = restApi.getAssetById(assetId);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                if (response.isSuccessful()) {
                    assetListCallback.onResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
            }
        });
    }

}
