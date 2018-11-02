package cn.cloudworkshop.miaoding.mvp.goods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import cn.cloudworkshop.miaoding.R;
import cn.cloudworkshop.miaoding.adapter.MyAdapter;
import cn.cloudworkshop.miaoding.base.BaseMvpActivity;
import cn.cloudworkshop.miaoding.bean.GoodsBean;
import cn.cloudworkshop.miaoding.utils.LoadingView;

public class MainActivity extends BaseMvpActivity<GoodsContract.Presenter> implements GoodsContract.View{
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private LoadingView loadingView;

    private List<GoodsBean.DataBean.itemDataBean> dataList = new ArrayList<>();
    private MyAdapter adapter;
    private int page = 1;
    //0:init, 1: refresh, 2:load
    private int type;
    @Override
    protected GoodsContract.Presenter initPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.rv);
        loadingView = findViewById(R.id.layout_loading);
        loadingView.setState(LoadingView.State.ing);
        loadingView.setOnRetryListener(new LoadingView.OnRetryListener() {
            @Override
            public void onRetry() {
                loadingView.setState(LoadingView.State.ing);
                page = 1;
                type = 0;
                mPresenter.initData(page, type);
            }
        });

        initView();
    }

    private void initView() {

        mPresenter.initData(page, type);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                type = 2;
                mPresenter.initData(page, type);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                type = 1;
                mPresenter.initData(page, type);
            }
        });

    }

    @Override
    public void hideLoading() {
        loadingView.setState(LoadingView.State.done);
    }

    @Override
    public void finishRefresh() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void finishLoad() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void loadError() {
        loadingView.setState(LoadingView.State.error);
    }

    @Override
    public void initView(List<GoodsBean.DataBean.itemDataBean> userList) {
        if (type != 2) {
            dataList.clear();
        }
        dataList.addAll(userList);
        adapter.notifyDataSetChanged();
    }
}
