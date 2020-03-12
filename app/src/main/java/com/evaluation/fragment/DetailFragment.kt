package com.evaluation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.evaluation.dagger.data.DataComponent
import com.evaluation.network.RestAdapter
import com.evaluation.retrofitkt.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
class DetailFragment : BaseFragment() {

    override val TAG = DetailFragment::class.java.canonicalName

    private var mRootView: View? = null

    private var assetId: Int = 0

    @Inject
    lateinit var restAdapter: RestAdapter

    @BindView(R.id.logo)
    lateinit var logoView: ImageView

    @BindView(R.id.title)
    lateinit var titleView: TextView

    @BindView(R.id.description)
    lateinit var descriptionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataComponent.Injector.component!!.inject(this)
        assetId = arguments?.getInt(EXTRA_ASSET_ID, NO_SELECTION) ?: NO_SELECTION
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            val view = inflater.inflate(R.layout.info_layout, container, false)
            ButterKnife.bind(this, view)
            mRootView = view
            loadAssetDetail(assetId)
        }
        return mRootView
    }

    private fun loadAssetDetail(assetId: Int) {
        restAdapter.restApiService!!.getAssetById(assetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ asset ->
                activity?.let {
                    Glide.with(it)
                        .load(BASE_IMAGE_URL + asset.posterPath)
                        .into(logoView)
                }
                titleView.text = asset.title
                descriptionView.text = asset.overview
            },
                {})
    }


    override fun onBackPressed(): Boolean {
        return false
    }

    companion object {

        const val NO_SELECTION = -1
        private const val EXTRA_ASSET_ID = "EXTRA_ASSET_ID"
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780"

        fun newInstance(assetId: Int): DetailFragment {
            val args = Bundle()
            args.putInt(EXTRA_ASSET_ID, assetId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}