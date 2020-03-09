package com.evaluation.model.asset;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssetList {

    @SerializedName("assets")
    private List<Asset> assetsList;

    public AssetList() {
    }

    public List<Asset> getAssetsList() {
        return assetsList;
    }
}
