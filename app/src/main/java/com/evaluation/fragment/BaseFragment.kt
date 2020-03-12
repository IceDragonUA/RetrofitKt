package com.evaluation.fragment

import androidx.fragment.app.Fragment

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
open class BaseFragment : Fragment() {

    open val TAG = this.javaClass.canonicalName

    open fun onBackPressed(): Boolean = true
}