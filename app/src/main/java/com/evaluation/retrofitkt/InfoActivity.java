package com.evaluation.retrofitkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.evaluation.dao.DataDao;
import com.evaluation.model.asset.Asset;
import com.evaluation.model.search.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public static void launchActivity(Context activityContext, SearchResult selectedSearchResult) {
        DataDao.getInstance().getAssetById(selectedSearchResult.getId(), result -> {
            Intent launchingIntent = new Intent(activityContext, InfoActivity.class);
            launchingIntent.putExtra(SELECTED_PROJECT_KEY, result);
            activityContext.startActivity(launchingIntent);
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
