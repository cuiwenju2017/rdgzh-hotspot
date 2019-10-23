package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.utils.HRMenuPopWindow;

/**
 * 招聘详情
 */
public class HRGiveJobActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrgive_job);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("招聘详情");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {//返回
            finish();
        } else if (i == R.id.ll_menu) {//菜单弹窗
            new HRMenuPopWindow(this).showAtBottom(ll_menu);
        }
    }
}
