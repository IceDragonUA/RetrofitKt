package com.evaluation.retrofitkt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evaluation.adapter.CustomListAdapter;
import com.evaluation.command.ICommand;
import com.evaluation.dao.DataDao;
import com.evaluation.model.search.SearchResult;

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

        loadAssetList();
    }

    private void loadAssetList() {
        DataDao.getInstance().getSearchList(searchResults -> {
            CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, searchResults, new ICommand<SearchResult>() {
                @Override
                public void execute(SearchResult selectedProject) {
                    InfoActivity.launchActivity(MainActivity.this, selectedProject);
                }
            });
            recycleView.setAdapter(adapter);
        });
    }
}
