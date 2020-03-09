package com.evaluation.dagger

import com.evaluation.retrofitkt.MainActivity

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
internal interface DataGraph {
    fun inject(mainActivity: MainActivity?)
}