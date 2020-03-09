package com.evaluation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.evaluation.model.asset.Asset;
import com.evaluation.retrofitkt.R;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private final Context context;
    private final List<Asset> assetList;
    private final LayoutInflater layoutInflater;

    public CustomPagerAdapter(Context context, List<Asset> assetList) {
        this.context = context;
        this.assetList = assetList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return assetList.size();
    }

    public Asset getItem(int position) {
        return assetList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);
        bind(itemView, getItem(position));
        container.addView(itemView);
        return itemView;
    }

    private void bind(View rootView, Asset asset) {
        Glide.with(context)
                .load(asset.getAssetUrl())
                .into((ImageView) rootView.findViewById(R.id.slide));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
