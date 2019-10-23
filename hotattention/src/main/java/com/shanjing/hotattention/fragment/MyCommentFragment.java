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
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.RecycleMyCommentAdapter;
import com.shanjing.hotattention.adapter.RecyclerCommentAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.CommentListBean;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 我的评论
 */
public class MyCommentFragment extends Fragment {

    private SmartRefreshLayout srl;
    private RecyclerView rv_comment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_comment, container, false);
        rv_comment = view.findViewById(R.id.rv_comment);
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
    private List<CommentListBean.DataBean> list = new ArrayList<>();

    private void initData() {
        RxHttpUtils
                .createApi(HotService.class)
                .getCommentList(1, "15767952761")
                .compose(Transformer.<CommentListBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<CommentListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentListBean commentListBean) {
                        list = commentListBean.getData();
                        //设置布局管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv_comment.setLayoutManager(linearLayoutManager);
                        RecycleMyCommentAdapter recycleMyCommentAdapter = new RecycleMyCommentAdapter(getActivity(), list);
                        rv_comment.setAdapter(recycleMyCommentAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("bbb", "onNext: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
