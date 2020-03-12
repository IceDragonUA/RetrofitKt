@file:Suppress("DEPRECATION")

package com.evaluation.navigation

import com.evaluation.fragment.BaseFragment
import com.evaluation.fragment.DetailFragment
import com.evaluation.fragment.MainFragment
import com.evaluation.retrofitkt.MainActivity
import com.evaluation.retrofitkt.R
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
class Navigator @Inject constructor() {

    lateinit var mActivity: MainActivity

    fun init(activity: MainActivity) {
        mActivity = activity
    }

    fun showMainFragment() {
        showContentFragment(MainFragment.newInstance())
    }

    fun showDetailFragment(assetId: Int) {
        showContentFragment(DetailFragment.newInstance(assetId))
    }

    private fun showContentFragment(contentFragment: BaseFragment) {
        val fragmentTransaction = mActivity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, contentFragment, contentFragment.TAG)
            .addToBackStack(contentFragment.TAG)
        fragmentTransaction.commitAllowingStateLoss()
    }

    val contentFragment: BaseFragment?
        get() = mActivity.fragmentManager.findFragmentById(R.id.fragment) as BaseFragment
}