package com.evaluation.network;

import com.evaluation.model.asset.AssetList;
import com.evaluation.model.client.ClientList;
import com.evaluation.model.project.ProjectList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("3ssdemo/prj/json/projects.php")
    Call<ProjectList> getProjectData();

    @GET("3ssdemo/prj/json/clients.php")
    Call<ClientList> getClientData();

    @GET("3ssdemo/prj/json/galleryAssets.php")
    Call<AssetList> getAssetByProject(@Query("projectId") int projectId);
}


