package com.evaluation.retrofitkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.evaluation.adapter.CustomPagerAdapter;
import com.evaluation.dao.DataDao;
import com.evaluation.model.asset.Asset;
import com.evaluation.model.asset.AssetType;
import com.evaluation.model.client.Client;
import com.evaluation.model.project.Project;
import com.evaluation.network.IDataLoadingResult;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    private static final String SELECTED_PROJECT_KEY = "selected_project";

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.logo)
    ImageView projectLogoView;

    @BindView(R.id.logoProgressBar)
    ProgressBar projectLogoProgressBar;

    @BindView(R.id.title)
    TextView projectNameView;

    @BindView(R.id.description)
    TextView projectDescriptionView;

    @BindView(R.id.technologies)
    TextView projectTechnologiesView;

    @BindView(R.id.supportedScreens)
    TextView projectSupportedScreensView;

    @BindView(R.id.solutionTypes)
    TextView projectSolutionTypesView;

    @BindView(R.id.clientName)
    TextView clientNameView;

    private Project selectedProject;

    public static void launchActivity(Context activityContext, Project selectedProject) {
        Intent launchingIntent = new Intent(activityContext, InfoActivity.class);
        launchingIntent.putExtra(SELECTED_PROJECT_KEY, selectedProject);
        activityContext.startActivity(launchingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        parseArgument();
        updateUiForInfo();
        loadClient();
        loadAssets();
    }

    private void parseArgument() {
        selectedProject = getIntent().getExtras().getParcelable(SELECTED_PROJECT_KEY);
    }

    private void updateUiForInfo() {
        projectNameView.setText(selectedProject.getProjectName());
        projectDescriptionView.setText(selectedProject.getProjectDescription());
        projectTechnologiesView.setText(Arrays.toString(selectedProject.getProjectTechnologies()));
        projectSupportedScreensView.setText(Arrays.toString(selectedProject.getProjectSupportedScreens()));
        projectSolutionTypesView.setText(Arrays.toString(selectedProject.getProjectSolutionTypes()));

        Glide.with(this)
                .load(selectedProject.getProjectImage().getProjectImageUrl())
                .into(projectLogoView);
    }

    private void loadClient() {

        DataDao.getInstance().getClientById(selectedProject.getClientId(), new IDataLoadingResult<Client>() {
            @Override
            public void onResult(Client result) {
                updateUiForClient(result);
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });

    }

    private void updateUiForClient(Client client) {
        clientNameView.setText(client.getClientName());
    }

    private void loadAssets() {

        DataDao.getInstance().getAssetsByProjectId(selectedProject.getProjectId(), AssetType.IMAGE, new IDataLoadingResult<List<Asset>>() {
            @Override
            public void onResult(List<Asset> result) {
                viewPager.setAdapter(new CustomPagerAdapter(InfoActivity.this, result));
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });

    }
}
