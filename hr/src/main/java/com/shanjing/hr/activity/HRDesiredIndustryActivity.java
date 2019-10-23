package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 期望行业
 */
public class HRDesiredIndustryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrdesired_industry);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("期望行业");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
