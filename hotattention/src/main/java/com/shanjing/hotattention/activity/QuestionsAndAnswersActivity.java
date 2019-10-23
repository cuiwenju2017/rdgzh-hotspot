package com.shanjing.hotattention.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.QuestionsAnswersAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.QuestionsAnswersListBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 问答
 */
public class QuestionsAndAnswersActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private RecyclerView rv_q_and_a;
    private SmartRefreshLayout srl;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_and_answers);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        initView();
        initData();
        refresh();
    }

    private void refresh() {
        //刷新数据
        srl.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
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
    private List<QuestionsAnswersListBean.DataBean> list = new ArrayList<>();

    private void initData() {
        RxHttpUtils
                .createApi(HotService.class)
                .getQuestionsAnswersList(id, 1)
                .compose(Transformer.<QuestionsAnswersListBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<QuestionsAnswersListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QuestionsAnswersListBean questionsAnswersListBean) {
                        list = questionsAnswersListBean.getData();
                        //设置管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv_q_and_a.setLayoutManager(linearLayoutManager);
                        //设置适配器
                        QuestionsAnswersAdapter questionsAnswersAdapter = new QuestionsAnswersAdapter(QuestionsAndAnswersActivity.this, list);
                        rv_q_and_a.setAdapter(questionsAnswersAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(QuestionsAndAnswersActivity.this, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rv_q_and_a = findViewById(R.id.rv_q_and_a);
        srl = findViewById(R.id.srl);
        ll_back.setOnClickListener(this);
        tv_title_name.setText("问答");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }
}
