package com.shanjing.hotattention.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.RecycleCollectAdapter;
import com.shanjing.hotattention.adapter.RecycleLikeAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.CollectListBean;
import com.shanjing.hotattention.bean.LikeListBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 我的收藏
 */
public class MyCollectFragment extends Fragment {

    private SmartRefreshLayout srl;
    private RecyclerView rv_collect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_collect, container, false);
        rv_collect = view.findViewById(R.id.rv_collect);
        srl = view.findViewById(R.id.srl);
        initData();
        refresh();
        return view;
    }

    private void refresh() {
        //刷新数据
        srl.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
                if (srl != null) {
                    srl.finishRefresh();
                }
            }
        });
    }

    private LoadingDialog loadingDialog;
    private List<CollectListBean.DataBean> list = new ArrayList<>();

    private void initData() {
        RxHttpUtils
                .createApi(HotService.class)
                .getCollectList(1, "15767952761")
                .compose(Transformer.<CollectListBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<CollectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectListBean collectListBean) {
                        list = collectListBean.getData();
                        //设置布局管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv_collect.setLayoutManager(linearLayoutManager);
                        RecycleCollectAdapter recycleCollectAdapter = new RecycleCollectAdapter(getActivity(), list);
                        rv_collect.setAdapter(recycleCollectAdapter);

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
}
