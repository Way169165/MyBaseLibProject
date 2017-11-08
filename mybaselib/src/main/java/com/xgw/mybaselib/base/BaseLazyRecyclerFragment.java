package com.xgw.mybaselib.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xgw.mybaselib.R;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by XieGuangwei on 2017/11/8.
 * 通用列表懒加载基类
 */

public abstract class BaseLazyRecyclerFragment<T> extends BaseLazyFragment {
    protected BaseRecyclerAdapter adapter;
    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView recyclerView;

    protected int LINEAR_LAYOUT_MANAGER_TYPE = 1001;
    protected int GRID_LAYOUT_MANAGER_TYPE = 1002;


    @Override
    public void initView() {
        if (getView() == null) {
            return;
        }
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefresh);
        if (getLayoutManagerType() == LINEAR_LAYOUT_MANAGER_TYPE) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else if (getLayoutManagerType() == GRID_LAYOUT_MANAGER_TYPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getGridLayoutManagerSpanCount()));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter = getAdapter();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handleData();
            }
        });
    }

    @Override
    protected void loadData() {
        handleData();
    }

    @Override
    public void initData() {

    }

    private void handleData() {
        Disposable disposable = getData().compose(RxSchedulers.<T>io_main())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        onResultSuccess(t);
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onResultError(e);
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposables.add(disposable);
    }

    protected abstract BaseRecyclerAdapter getAdapter();

    protected abstract Observable<T> getData();

    protected abstract void onResultSuccess(T result);

    protected abstract void onResultError(Throwable e);

    protected abstract int getLayoutManagerType();

    protected abstract int getGridLayoutManagerSpanCount();
}
