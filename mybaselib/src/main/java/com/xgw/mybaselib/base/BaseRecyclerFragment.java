package com.xgw.mybaselib.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xgw.mybaselib.R;
import com.xgw.mybaselib.rxhttp.helper.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by XieGuangwei on 2017/11/7.
 * 列表拉取数据的fragment基类
 */

public abstract class BaseRecyclerFragment<T, K> extends BaseLazyFragment {
    protected BaseRecyclerAdapter<K> adapter;
    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView recyclerView;

    protected int LINEAR_LAYOUT_MANAGER_TYPE = 1001;
    protected int GRID_LAYOUT_MANAGER_TYPE = 1002;

    private int pageNo = 1;

    @Override
    public void initView() {
        if (getView() == null) {
            return;
        }
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefresh);
        if (getSwipeRefreshSchemaColors() == null || getSwipeRefreshSchemaColors().length == 0) {
            swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        } else {
            swipeRefresh.setColorSchemeColors(getSwipeRefreshSchemaColors());
        }
        if (getLayoutManagerType() == LINEAR_LAYOUT_MANAGER_TYPE) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else if (getLayoutManagerType() == GRID_LAYOUT_MANAGER_TYPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getGridLayoutManagerSpanCount()));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        adapter = getAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        int pageSize = 10;
        adapter.setPageSize(pageSize);
        //下拉刷新
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新时禁用上拉加载
                adapter.setEnableLoadMore(false);
                pageNo = 1;
                handleData(false);
            }
        });
        //设置是否可上拉加载
        adapter.setEnableLoadMore(isEnableLoadMore());
        //如果能上拉加载，监听上拉加载
        if (isEnableLoadMore()) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    handleData(true);
                }
            }, recyclerView);
        }
        //不用懒加载，fragment初始化直接加载数据
        if (!isLoadLazy()) {
            handleData(false);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    protected void startLazyLoad() {
        //使用懒加载，初始化view企鹅UI可见之后才加载数据
        if (isLoadLazy()) {
            handleData(false);
        }
    }

    private void handleData(final boolean isLoadMore) {
        Disposable disposable = getData(pageNo).compose(RxSchedulers.<T>io_main())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T t) {
                        onResultSuccess(t, pageNo);
                        swipeRefresh.setRefreshing(false);
                        pageNo++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        onResultError(e);
                        swipeRefresh.setRefreshing(false);
                        if (isLoadMore) {
                            adapter.showMorePageData(null, pageNo);
                        } else {
                            adapter.showSinglePageData(null);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposables.add(disposable);
    }

    /**
     * 是否开启懒加载
     *
     * @return
     */
    protected abstract boolean isLoadLazy();

    /**
     * 是否允许上拉加载更多
     *
     * @return
     */
    protected abstract boolean isEnableLoadMore();

    /**
     * 获取适配器
     *
     * @return
     */
    protected abstract BaseRecyclerAdapter<K> getAdapter();

    /**
     * 获取数据并返回Observable对象
     *
     * @param pageNo
     * @return
     */
    protected abstract Observable<T> getData(int pageNo);

    /**
     * 获取数据成功并返回
     *
     * @param result
     * @param pageNo
     */
    protected abstract void onResultSuccess(T result, int pageNo);

    /**
     * 获取数据失败并返回
     *
     * @param e
     */
    protected abstract void onResultError(Throwable e);

    /**
     * 获取recyclerView布局类型LINEAR_LAYOUT_MANAGER_TYPE/GRID_LAYOUT_MANAGER
     *
     * @return
     */
    protected abstract int getLayoutManagerType();

    /**
     * 获取列数（如果是GRID_LAYOUT_MANAGER）
     *
     * @return
     */
    protected abstract int getGridLayoutManagerSpanCount();

    /**
     * 获取下拉刷新渐变颜色数组
     *
     * @return
     */
    protected abstract int[] getSwipeRefreshSchemaColors();
}
