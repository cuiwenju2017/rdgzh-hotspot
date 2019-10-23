package com.shanjing.hr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.utils.HRMenuPopWindow;

/**
 * 求职详情
 */
public class JobWantedParticularsActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title, tv_name;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_wanted_particulars);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        tv_name = findViewById(R.id.tv_name);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("详情");
        tv_name.setText(name);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {//返回
            finish();
        } else if (i == R.id.ll_menu) {//点击菜单弹窗
            new HRMenuPopWindow(this).showAtBottom(ll_menu);
        }
    }
}
