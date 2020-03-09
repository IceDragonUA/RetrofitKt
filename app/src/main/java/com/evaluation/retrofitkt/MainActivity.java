package com.evaluation.retrofitkt;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evaluation.adapter.CustomListAdapter;
import com.evaluation.dao.DataDao;
import com.evaluation.model.project.Project;
import com.evaluation.network.IDataLoadingResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        loadProjects();
    }

    private void loadProjects() {

        DataDao.getInstance().getProjects(new IDataLoadingResult<List<Project>>() {
            @Override
            public void onResult(List<Project> projects) {
                CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, projects, new ICommand<Project>() {
                    @Override
                    public void execute(Project selectedProject) {
                        InfoActivity.launchActivity(MainActivity.this, selectedProject);
                    }
                });
                recycleView.setAdapter(adapter);
                Log.e("onResult", "onResult: " + projects.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
            }
        });
    }
}
