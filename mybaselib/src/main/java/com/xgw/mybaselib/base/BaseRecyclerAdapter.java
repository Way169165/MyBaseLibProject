package com.xgw.mybaselib.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xgw.mybaselib.CustomLoadMoreView;
import com.xgw.mybaselib.R;

import java.util.List;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 列表适配器基类
 */

public abstract class BaseRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    //分页时每页数量
    private int pageSize = 10;

    public BaseRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, RecyclerView recyclerView) {
        super(layoutResId, data);
        bindToRecyclerView(recyclerView);
        setEmptyView(R.layout.loading_layout);
        setLoadMoreView(new CustomLoadMoreView());
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 展示一页（不分页）
     *
     * @param data
     */
    public void showSinglePageData(@Nullable List<T> data) {
        if (data == null || data.size() == 0) {
            setNewData(null);
            setEmptyView(R.layout.no_data_layout);
        } else {
            setNewData(data);
        }
    }

    /**
     * 展示多页数据（分页）
     *
     * @param data
     */
    public void showMorePageData(@Nullable List<T> data, int pageNo) {
        if (data == null || data.size() == 0) {
            if (pageNo == 1) {
                setNewData(null);
                setEmptyView(R.layout.no_data_layout);
            } else {
                loadMoreFail();
            }
            return;
        }
        if (pageNo == 1) {
            setNewData(data);
        } else {
            addData(data);
            loadMoreComplete();
        }

        if (data.size() < pageSize) {
            loadMoreEnd();
        }
    }
}
