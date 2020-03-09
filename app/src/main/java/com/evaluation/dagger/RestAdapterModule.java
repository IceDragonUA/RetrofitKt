package com.evaluation.dagger;

import com.evaluation.network.RestAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
@Module
public class RestAdapterModule {

    private static RestAdapterModule instance;

    RestAdapterModule() {
    }

    public static RestAdapterModule getInstance() {
        if (instance == null) {
            instance = new RestAdapterModule();
        }
        return instance;
    }

    @Provides
    @Singleton
    RestAdapter provideControlCenterRetrofitAdapter() {
        return new RestAdapter();
    }

}
