package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 添加公司地址
 */
public class HRAddCompanyAddressActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hradd_company_address);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        ll_hr_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
