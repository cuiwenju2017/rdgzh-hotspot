package com.shanjing.hr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.JurisdictionSettingAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * 发布权限设置
 */
public class HRSignJurisdictionActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title;
    private RecyclerView rv_jurisdiction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrsign_jurisdiction);
        initView();
    }

    private List<String> list = Arrays.asList("公开", "私密", "部分可见", "不给谁看");
    private List<String> list2 = Arrays.asList("(默认)", "", "", "");
    private List<String> list3 = Arrays.asList("所有人可见", "仅自己可见", "选中的人可见", "选中的人不可见");

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title = findViewById(R.id.tv_title);
        rv_jurisdiction = findViewById(R.id.rv_jurisdiction);
        ll_back.setOnClickListener(this);
        tv_title.setText("权限设置");

        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_jurisdiction.setLayoutManager(linearLayoutManager);
        //设置适配器
        final JurisdictionSettingAdapter jurisdictionSettingAdapter = new JurisdictionSettingAdapter(this, list, list2, list3);
        rv_jurisdiction.setAdapter(jurisdictionSettingAdapter);

        jurisdictionSettingAdapter.setOnItemClickListener(new JurisdictionSettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<String> holder, int pos) {
                //传到适配器  （适配器调用方法）
                jurisdictionSettingAdapter.getIndex(pos);
                //刷新适配器
                jurisdictionSettingAdapter.notifyDataSetChanged();
                if (pos == 2) {
                    startActivity(new Intent(HRSignJurisdictionActivity.this, HRChooseLinkmanActivity.class));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {
            finish();
        }
    }
}
