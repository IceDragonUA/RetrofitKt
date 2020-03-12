package com.evaluation.dagger.data

import com.evaluation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Vladyslav Havrylenko
 * @since 10.03.2020
 */
@Module
object  NavigatorModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator()
}