package com.evaluation.model.client;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientList {

    @SerializedName("clients")
    private List<Client> clientList;

    public ClientList() {
    }

    public List<Client> getClientList() {
        return clientList;
    }
}
