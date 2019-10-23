package com.shanjing.hr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 教育经历
 */
public class HREducationExperienceActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title, tv_add_education_experience;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hreducation_experience);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        tv_add_education_experience = findViewById(R.id.tv_add_education_experience);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        tv_add_education_experience.setOnClickListener(this);
        tv_title.setText("教育经历");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        ll_menu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.tv_add_education_experience) {//添加教育经历
            startActivity(new Intent(this, HRAddEducationExperienceActivity.class));
        }
    }
}
