package com.shanjing.hotattention.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.TopicAdapter;
import com.shanjing.hotattention.adapter.TopicSearchAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.AddTopicBean;
import com.shanjing.hotattention.bean.TopicListBean;
import com.shanjing.hotattention.bean.TopicSearchBean;
import com.shanjing.hotattention.view.ClearTextEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 话题选择
 */
public class TopicActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name, tv_new_topic;
    private RecyclerView rv_topic;
    private ClearTextEditText et_topic;
    private TextView tv;
    private View v;
    private TopicSearchAdapter topicSearchAdapter;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        initView();
        initData();
        initDataTopicList();
    }

    private void initDataTopicList() {
        //热门话题
        RxHttpUtils
                .createApi(HotService.class)
                .getTopicList()
                .compose(Transformer.<TopicListBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<TopicListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TopicListBean topicListBean) {
                        list = topicListBean.getData();
                        //设置管理器
                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                        rv_topic.setLayoutManager(linearLayoutManager);
                        //设置适配器
                        TopicAdapter topicAdapter = new TopicAdapter(TopicActivity.this, list);
                        rv_topic.setAdapter(topicAdapter);
                        //item点击事件
                        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(TopicListBean.DataBean holder, int pos) {
                                Intent intent = new Intent(TopicActivity.this, IssueActivity.class);
                                intent.putExtra("topicname", holder.getName());
                                intent.putExtra("topicid", holder.getId());
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(TopicActivity.this, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private LoadingDialog loadingDialog;
    private List<TopicListBean.DataBean> list = new ArrayList<>();
    private List<TopicSearchBean.DataBean> listTopicSearch = new ArrayList<>();
    Gson gson = new Gson();

    private void initData() {
        et_topic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tv.setVisibility(View.VISIBLE);//显示文字
                    v.setVisibility(View.VISIBLE);//显示View
                    ll.setVisibility(View.GONE);//隐藏新话题
                    rv_topic.setVisibility(View.VISIBLE);//显示话题列表
                    initDataTopicList();//热门话题
                } else {//搜索话题
                    RxHttpUtils
                            .createApi(HotService.class)
                            .getTopicSearchList("" + s)
                            .compose(Transformer.<TopicSearchBean>switchSchedulers(loadingDialog))
                            .subscribe(new Observer<TopicSearchBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(TopicSearchBean topicSearchBean) {
                                    listTopicSearch = topicSearchBean.getData();
                                    if (topicSearchBean.getData().size() < 1) {//搜索不到话题
                                        tv.setVisibility(View.GONE);//隐藏文字
                                        v.setVisibility(View.GONE);//隐藏View
                                        rv_topic.setVisibility(View.GONE);//隐藏话题列表
                                        ll.setVisibility(View.VISIBLE);//显示新话题
                                        tv_new_topic.setText("#" + s + "#");//设置新话题名称
                                        //新话题的点击事件
                                        ll.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //创建新话题
                                                HashMap<String, String> paramsMap = new HashMap<>();
                                                paramsMap.put("name", "" + s);//话题名称
                                                String info = gson.toJson(paramsMap);
                                                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                                                RxHttpUtils
                                                        .createApi(HotService.class)
                                                        .addTopic(body)
                                                        .compose(Transformer.<AddTopicBean>switchSchedulers(loadingDialog))
                                                        .subscribe(new Observer<AddTopicBean>() {
                                                            @Override
                                                            public void onSubscribe(Disposable d) {

                                                            }

                                                            @Override
                                                            public void onNext(AddTopicBean topic) {
                                                                Intent intent = new Intent(TopicActivity.this, IssueActivity.class);
                                                                intent.putExtra("topicname", "" + topic.getData().getName());
                                                                intent.putExtra("topicid", "" + topic.getData().getId());
                                                                startActivity(intent);
                                                                finish();
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                ToastUtil.showShort(TopicActivity.this, "" + e);
                                                            }

                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });


                                            }
                                        });
                                    } else {//搜索到话题
                                        tv.setVisibility(View.GONE);//隐藏文字
                                        v.setVisibility(View.GONE);//隐藏View
                                        rv_topic.setVisibility(View.VISIBLE);//显示话题列表
                                        ll.setVisibility(View.GONE);//隐藏新话题
                                        //设置布局管理器
                                        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                                        rv_topic.setLayoutManager(linearLayoutManager);
                                        //设置适配器
                                        topicSearchAdapter = new TopicSearchAdapter(TopicActivity.this, listTopicSearch);
                                        rv_topic.setAdapter(topicSearchAdapter);
                                        //item点击事件
                                        topicSearchAdapter.setOnItemClickListener(new TopicSearchAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(TopicSearchBean.DataBean holder, int pos) {
                                                Intent intent = new Intent(TopicActivity.this, IssueActivity.class);
                                                intent.putExtra("topicname", holder.getName());
                                                intent.putExtra("topicid", holder.getId());
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onError(Throwable e) {
                                    ToastUtil.showShort(TopicActivity.this, "" + e);
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rv_topic = findViewById(R.id.rv_topic);
        et_topic = findViewById(R.id.et_topic);
        tv = findViewById(R.id.tv);
        v = findViewById(R.id.v);
        ll = findViewById(R.id.ll);
        tv_new_topic = findViewById(R.id.tv_new_topic);
        ll_back.setOnClickListener(this);
        ll.setOnClickListener(this);
        tv_title_name.setText("话题选择");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {
            startActivity(new Intent(this, IssueActivity.class));
            finish();
        }
    }
}
