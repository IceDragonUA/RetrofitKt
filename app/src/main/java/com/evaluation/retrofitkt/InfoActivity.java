package com.evaluation.retrofitkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.evaluation.model.asset.Asset;
import com.evaluation.model.search.SearchResult;
import com.evaluation.network.RestAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InfoActivity extends AppCompatActivity {

    private static final String SELECTED_PROJECT_KEY = "selected_project";
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780";

    @BindView(R.id.logo)
    ImageView logoView;

    @BindView(R.id.title)
    TextView titleView;

    @BindView(R.id.description)
    TextView descriptionView;

    private Asset selectedAsset;

    public static void launchActivity(Context activityContext, SearchResult selectedSearchResult, RestAdapter restAdapter) {
        restAdapter.getRestApiService().getAssetById(selectedSearchResult.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Asset>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Asset asset) {
                        Intent launchingIntent = new Intent(activityContext, InfoActivity.class);
                        launchingIntent.putExtra(SELECTED_PROJECT_KEY, asset);
                        activityContext.startActivity(launchingIntent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        parseArgument();

        loadInfo();
    }

    private void parseArgument() {
        selectedAsset = getIntent().getExtras().getParcelable(SELECTED_PROJECT_KEY);
    }

    private void loadInfo() {
        Glide.with(getApplicationContext())
                .load(BASE_IMAGE_URL + selectedAsset.getPosterPath())
                .into(logoView);

        titleView.setText(selectedAsset.getTitle());
        descriptionView.setText(selectedAsset.getDescription());

    }
}
