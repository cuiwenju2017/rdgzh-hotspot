package com.shanjing.hotattention.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.RecycleHomeRightAdapter;
import com.shanjing.hotattention.adapter.RecyclerLeftAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.HomeBean;
import com.shanjing.hotattention.bean.HomeClassifyBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 热点关注首页
 */
@SuppressLint("ValidFragment")
public class HotHomeFragment extends BaseFragment {

    private RecyclerView rv_right, rv_left;
    private LinearLayout ll_nav;
    private boolean isShowOrNot = false;
    private LinearLayout ll;
    private String id;
    private SmartRefreshLayout srl;
    private RecycleHomeRightAdapter recycleHomeRightAdapter;
    private RecyclerLeftAdapter recyclerLeftAdapter;
    private LoadingDialog loadingDialog;
    private List<HomeBean.DataBean> list = new ArrayList<>();
    private List<HomeClassifyBean.DataBean> dataBeans = new ArrayList<>();
    private int page = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_hot_home;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        rv_right = view.findViewById(R.id.rv_right);
        ll_nav = view.findViewById(R.id.ll_nav);
        ll = view.findViewById(R.id.ll);
        rv_left = view.findViewById(R.id.rv_left);
        srl = view.findViewById(R.id.srl);
        initView();
        fetchData();
        initDataRight();
        //设置布局管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_left.setLayoutManager(linearLayoutManager);
        recyclerLeftAdapter = new RecyclerLeftAdapter(getActivity(), dataBeans);
        rv_left.setAdapter(recyclerLeftAdapter);

        recyclerLeftAdapter.setOnItemClickListener(new RecyclerLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HomeClassifyBean.DataBean holder, int pos) {
                list.clear();
                page = 1;
                id = dataBeans.get(pos).getId();
                initDataRight();
            }
        });

        //设置布局管理器
        StaggeredGridLayoutManager linearLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_right.setLayoutManager(linearLayoutManager2);
        recycleHomeRightAdapter = new RecycleHomeRightAdapter(getActivity(), list);
        rv_right.setAdapter(recycleHomeRightAdapter);
    }

    private void initView() {
        //设置分类可隐藏
        ll_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowOrNot) {
                    ll.setVisibility(View.GONE);
                    isShowOrNot = true;
                } else {
                    ll.setVisibility(View.VISIBLE);
                    isShowOrNot = false;
                }
            }
        });

        //下拉刷新
        srl.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                list.clear();
                page = 1;
                initDataRight();
                if (srl != null) {
                    refreshLayout.finishRefresh();
                }
            }
        });

        //上拉加载
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                initDataRight();
                refreshlayout.finishLoadMore(2000);
            }
        });
    }

    @Override
    public void fetchData() {
        //loadingDialog = new LoadingDialog(getActivity());
        //左侧分类
        RxHttpUtils
                .createApi(HotService.class)
                .getHomeClassifyData()
                .compose(Transformer.<HomeClassifyBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<HomeClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeClassifyBean homeClassifyBean) {
                        dataBeans.addAll(homeClassifyBean.getData());
                        recyclerLeftAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(getActivity(), "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //右侧数据
    public void initDataRight() {
        RxHttpUtils
                .createApi(HotService.class)
                .getHomeData(page, id)
                .compose(Transformer.<HomeBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        list.addAll(homeBean.getData());
                        recycleHomeRightAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(getActivity(), "" + e);
                        Log.i("aaa", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
