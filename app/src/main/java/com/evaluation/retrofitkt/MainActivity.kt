package com.evaluation.retrofitkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evaluation.dagger.data.*
import com.evaluation.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mNavigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataComponent.Injector.init()

        setContentView(R.layout.activity_main)

        DataComponent.Injector.component!!.inject(this)

        mNavigator.init(this)
        mNavigator.showMainFragment()
    }

    override fun onBackPressed() {
        val currentFragment = mNavigator.contentFragment

        // Dispatch back event to the current fragment.
        if (currentFragment != null && currentFragment.onBackPressed()) {
            super.onBackPressed()
        }
        super.onBackPressed()
    }
}