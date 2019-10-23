package com.shanjing.hr.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 公司招聘通知与提醒
 */
public class HRCompanyInformRemindActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrcompany_inform_remind);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("通知与提醒");
        ll_menu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }

}
