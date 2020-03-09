package com.evaluation.retrofitkt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evaluation.adapter.CustomListAdapter;
import com.evaluation.dagger.DataComponent;
import com.evaluation.model.search.SearchList;
import com.evaluation.network.RestAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    RestAdapter restAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataComponent.Injector.init();

        setContentView(R.layout.activity_main);

        DataComponent.Injector.getComponent().inject(this);
        ButterKnife.bind(this);

        recycleView.setLayoutManager(new LinearLayoutManager(this));

        loadAssetList();
    }

    private void loadAssetList() {
        restAdapter.getRestApiService().getSearchData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SearchList searchList) {
                        CustomListAdapter adapter = new CustomListAdapter(
                                MainActivity.this,
                                searchList.getSearchList(),
                                selectedProject -> InfoActivity.launchActivity(
                                        MainActivity.this,
                                        selectedProject,
                                        restAdapter
                                )
                        );
                        recycleView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
