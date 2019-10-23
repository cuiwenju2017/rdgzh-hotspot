package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 职位管理
 */
public class HRJobManageActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrjob_manage);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        tv_finished = findViewById(R.id.tv_finished);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("职位管理");
        tv_finished.setText("编辑");
        tv_finished.setTextSize(15);
        tv_finished.setTextColor(getResources().getColor(R.color.color_text));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
