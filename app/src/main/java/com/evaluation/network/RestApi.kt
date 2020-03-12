package com.evaluation.network

import com.evaluation.model.asset.Asset
import com.evaluation.model.search.SearchList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Vladyslav Havrylenko
 * @since 12.03.2020
 */
interface RestApi {

    @GET("search/movie?api_key=f7262134aadc3f618b7c4bfac62b4194&language=en-US&query=h&page=1&include_adult=false")
    fun getSearchData(): Observable<SearchList>

    @GET("movie/{assetId}?api_key=f7262134aadc3f618b7c4bfac62b4194&language=en-US")
    fun getAssetById(@Path("assetId") assetId: Int): Observable<Asset>

}