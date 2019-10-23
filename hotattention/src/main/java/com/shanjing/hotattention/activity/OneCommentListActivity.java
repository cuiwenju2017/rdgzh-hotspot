package com.shanjing.hotattention.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.RecyclerOneCommentListAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.OneCommentListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 首页更多评论列表
 */
public class OneCommentListActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private String news_id;
    private RecyclerView rv_comment;
    private SmartRefreshLayout srl;
    private RecyclerOneCommentListAdapter recyclerOneCommentListAdapter;
    private int page = 1;
    private LoadingDialog loadingDialog;
    private List<OneCommentListBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_comment_list);
        Intent intent = getIntent();
        news_id = intent.getStringExtra("news_id");
        initView();
        initData();
        //设置评论布局管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_comment.setLayoutManager(linearLayoutManager);
        //设置适配器
        recyclerOneCommentListAdapter = new RecyclerOneCommentListAdapter(OneCommentListActivity.this, list);
        rv_comment.setAdapter(recyclerOneCommentListAdapter);
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rv_comment = findViewById(R.id.rv_comment);
        srl = findViewById(R.id.srl);
        ll_back.setOnClickListener(this);
        tv_title_name.setText("评论");

        //下拉刷新
        srl.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                list.clear();
                page = 1;
                initData();
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
                initData();
                refreshlayout.finishLoadMore(2000);
            }
        });

    }

    private void initData() {
        RxHttpUtils
                .createApi(HotService.class)
                .getOneCommentList(page, news_id)
                .compose(Transformer.<OneCommentListBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<OneCommentListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OneCommentListBean oneCommentListBean) {
                        if (oneCommentListBean.getData().size() < 1) {
                            Toast.makeText(OneCommentListActivity.this, "亲，到底啦", Toast.LENGTH_SHORT).show();
                        } else {
                            list.addAll(oneCommentListBean.getData());
                            recyclerOneCommentListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(OneCommentListActivity.this, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }

}
