package com.evaluation.dao;

import com.evaluation.model.asset.Asset;
import com.evaluation.model.asset.AssetList;
import com.evaluation.model.asset.AssetType;
import com.evaluation.model.client.Client;
import com.evaluation.model.client.ClientList;
import com.evaluation.model.project.Project;
import com.evaluation.model.project.ProjectList;
import com.evaluation.network.IDataLoadingResult;
import com.evaluation.network.RestApi;
import com.evaluation.network.RestAdapter;

import java.util.ArrayList;
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

    public void getProjects(final IDataLoadingResult<List<Project>> projectListCallback) {
        Call<ProjectList> call = restApi.getProjectData();
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {
                if (response.isSuccessful()) {
                    ProjectList projectList = response.body();
                    projectListCallback.onResult(projectList.getProjectList());
                } else {
                    projectListCallback.onFailure(new Exception("Unsuccessful data loading. Failure code [" + response.message() + "]"));
                }
            }

            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {
                projectListCallback.onFailure(t);
            }
        });
    }

    public void getClientById(final int clientId, final IDataLoadingResult<Client> clientListCallback) {
        Call<ClientList> call = restApi.getClientData();
        call.enqueue(new Callback<ClientList>() {
            @Override
            public void onResponse(Call<ClientList> call, Response<ClientList> response) {
                if (response.isSuccessful()) {
                    ClientList clientsWrapper = response.body();
                    Client clientById = findClientById(clientId, clientsWrapper.getClientList());
                    if (clientById != null) {
                        clientListCallback.onResult(clientById);
                    } else {
                        clientListCallback.onFailure(new Exception("Unsuccessful data loading. Failure code [" + response.message() + "]"));
                    }
                } else {
                    clientListCallback.onFailure(new Exception("Unsuccessful data loading. Failure code [" + response.message() + "]"));
                }
            }

            @Override
            public void onFailure(Call<ClientList> call, Throwable t) {
                clientListCallback.onFailure(t);
            }
        });

    }

    private Client findClientById(int clientId, List<Client> clientList) {
        for (Client client : clientList) {
            if (clientId == client.getClientId()) {
                return client;
            }
        }
        return null;
    }

    public void getAssetsByProjectId(final int projectId, final AssetType assetType, final IDataLoadingResult<List<Asset>> assetListCallback) {
        Call<AssetList> call = restApi.getAssetByProject(projectId);
        call.enqueue(new Callback<AssetList>() {
            @Override
            public void onResponse(Call<AssetList> call, Response<AssetList> response) {
                if (response.isSuccessful()) {
                    List<Asset> assetsList = response.body().getAssetsList();
                    List<Asset> filtrated = filterAssetsByAssetType(assetsList, assetType);
                    assetListCallback.onResult(filtrated);
                } else {
                    assetListCallback.onFailure(new Exception("Unsuccessful data loading. Failure code [" + response.message() + "]"));
                }
            }

            @Override
            public void onFailure(Call<AssetList> call, Throwable t) {

            }
        });
    }

    private List<Asset> filterAssetsByAssetType(List<Asset> sourceAssetsList, AssetType assetType) {
        List<Asset> res = new ArrayList<>();
        for (Asset asset : sourceAssetsList) {
            if (AssetType.fromString(asset.getAssetType()) == assetType) {
                res.add(asset);
            }
        }
        return res;
    }

}
