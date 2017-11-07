package com.xgw.mybaselibproject;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xgw.mybaselib.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 */

public class RecyclerTestAdapter extends BaseRecyclerAdapter<Gank> {
    public RecyclerTestAdapter(@LayoutRes int layoutResId, @Nullable List<Gank> data, RecyclerView recyclerView) {
        super(layoutResId, data, recyclerView);
    }

    @Override
    protected void convert(BaseViewHolder helper, Gank item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.item_img));
    }
}
