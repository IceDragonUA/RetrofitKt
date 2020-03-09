package com.evaluation.network;


public interface IDataLoadingResult<T> {

    void onResult(T result);

    void onFailure(Throwable ex);

}
