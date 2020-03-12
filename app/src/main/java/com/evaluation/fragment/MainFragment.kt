package com.evaluation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.evaluation.adapter.CustomListAdapter
import com.evaluation.command.ICommand
import com.evaluation.dagger.data.DataComponent
import com.evaluation.model.search.SearchResult
import com.evaluation.navigation.Navigator
import com.evaluation.network.RestAdapter
import com.evaluation.retrofitkt.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
class MainFragment : BaseFragment() {

    override val TAG = MainFragment::class.java.canonicalName

    private var mRootView: View? = null

    @Inject
    lateinit var mNavigator: Navigator

    @Inject
    lateinit var restAdapter: RestAdapter

    @BindView(R.id.recycler_view)
    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataComponent.Injector.component!!.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            val view = inflater.inflate(R.layout.main_layout, container, false)
            ButterKnife.bind(this, view)
            mRootView = view
            recycleView.layoutManager = LinearLayoutManager(activity)
            loadAssetList()
        }
        return mRootView
    }

    @SuppressLint("CheckResult")
    private fun loadAssetList() {
        restAdapter.restApiService!!.getSearchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchList ->
                    val adapter = CustomListAdapter(
                        activity,
                        searchList.searchResultList,
                        object : ICommand<SearchResult> {
                            override fun execute(param: SearchResult) {
                                mNavigator.showDetailFragment(param.id)
                            }
                        }
                    )
                    recycleView.adapter = adapter
                },
                {})
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}