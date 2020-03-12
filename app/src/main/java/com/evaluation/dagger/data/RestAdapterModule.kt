package com.evaluation.dagger.data

import com.evaluation.network.RestAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Vladyslav Havrylenko
 * @since 10.03.2020
 */
@Module
object  RestAdapterModule {
    @Provides
    @Singleton
    fun provideRestAdapter(): RestAdapter = RestAdapter()
}