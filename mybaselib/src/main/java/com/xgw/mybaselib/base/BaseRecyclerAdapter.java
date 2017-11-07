package com.xgw.mybaselib.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xgw.mybaselib.R;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 列表适配器基类
 */

public abstract class BaseRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public BaseRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, RecyclerView recyclerView) {
        super(layoutResId, data);
        bindToRecyclerView(recyclerView);
        setEmptyView(R.layout.loading_layout);
    }

    @Override
    public void setNewData(@Nullable List<T> data) {
        if (data == null || data.size() == 0) {
            setEmptyView(R.layout.no_data_layout);
            super.setNewData(null);
        } else {
            super.setNewData(data);
        }
    }
}
