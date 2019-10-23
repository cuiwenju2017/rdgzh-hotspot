package com.shanjing.hotattention.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.RecycleSearchAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.SearchListBean;
import com.shanjing.hotattention.view.ClearTextEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private ClearTextEditText et_search;
    private RecyclerView rv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
    }

    private LoadingDialog loadingDialog;
    private List<SearchListBean.DataBean> list = new ArrayList<>();

    private void initData() {
        tv_title_name.setText("搜索");
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RxHttpUtils
                        .createApi(HotService.class)
                        .getSearchList(1, "" + s)
                        .compose(Transformer.<SearchListBean>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<SearchListBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(SearchListBean searchListBean) {
                                list = searchListBean.getData();
                                if (list.size() > 1) {
                                    rv_search.setVisibility(View.VISIBLE);
                                    //设置布局管理器
                                    StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                                    rv_search.setLayoutManager(linearLayoutManager);
                                    //设置适配器
                                    RecycleSearchAdapter recycleSearchAdapter = new RecycleSearchAdapter(SearchActivity.this, list);
                                    rv_search.setAdapter(recycleSearchAdapter);
                                } else {
                                    rv_search.setVisibility(View.GONE);
                                    ToastUtil.showShort(SearchActivity.this, "暂无该内容");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showShort(SearchActivity.this, "" + e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        et_search = findViewById(R.id.et_search);
        rv_search = findViewById(R.id.rv_search);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }

}
