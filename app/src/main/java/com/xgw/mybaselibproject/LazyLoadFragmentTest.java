package com.xgw.mybaselibproject;

import com.xgw.mybaselib.base.BaseLazyFragment;
import com.xgw.mybaselib.base.BaseLazyRecyclerFragment;
import com.xgw.mybaselib.base.BaseRecyclerAdapter;
import com.xgw.mybaselib.rxhttp.RxHttpUtils;
import com.xgw.mybaselib.rxhttp.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XieGuangwei on 2017/11/8.
 */

public class LazyLoadFragmentTest extends BaseLazyRecyclerFragment<BaseResponse<List<Gank>>> {
    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new RecyclerTestAdapter(R.layout.item_recycler_test, null, recyclerView);
    }

    @Override
    protected Observable<BaseResponse<List<Gank>>> getData() {
        return RxHttpUtils.getSingleConfig()
                .setBaseUrl(UrlConstants.BASE_URL)
                .createApi(ApiService.class)
                .getMeizhiData(10);
    }

    @Override
    protected void onResultSuccess(BaseResponse<List<Gank>> result) {
        adapter.setNewData(result.getResults());
    }

    @Override
    protected void onResultError(Throwable e) {
        adapter.setNewData(null);
    }

    @Override
    protected int getLayoutManagerType() {
        return GRID_LAYOUT_MANAGER_TYPE;
    }

    @Override
    protected int getGridLayoutManagerSpanCount() {
        return 2;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycler_view_layout;
    }
}
