package com.shanjing.hr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.utils.HRMenuResumDetailsPopWindow;

/**
 * 简历详情
 */
public class HRResumDetailsActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu, ll_job_experience, ll_education_experience, ll_project_experience;
    private TextView tv_title, tv_name;
    private String name;
    private RelativeLayout rl_job_experience_open, rl_education_experience_open, rl_project_experience_open;
    private ImageView iv_job_experience, iv_education_experience, iv_project_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrresum_details);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        tv_name = findViewById(R.id.tv_name);
        rl_job_experience_open = findViewById(R.id.rl_job_experience_open);
        ll_job_experience = findViewById(R.id.ll_job_experience);
        iv_job_experience = findViewById(R.id.iv_job_experience);
        rl_education_experience_open = findViewById(R.id.rl_education_experience_open);
        rl_project_experience_open = findViewById(R.id.rl_project_experience_open);
        iv_education_experience = findViewById(R.id.iv_education_experience);
        iv_project_experience = findViewById(R.id.iv_project_experience);
        ll_education_experience = findViewById(R.id.ll_education_experience);
        ll_project_experience = findViewById(R.id.ll_project_experience);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        rl_job_experience_open.setOnClickListener(this);
        rl_education_experience_open.setOnClickListener(this);
        rl_project_experience_open.setOnClickListener(this);
        tv_title.setText("详情");
        tv_name.setText(name);
    }

    private boolean isOpenJobExperience = false;
    private boolean isOpenEducationExperience = false;
    private boolean isOpenProjectExperience = false;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//菜单弹窗
            new HRMenuResumDetailsPopWindow(this).showAtBottom(ll_menu);
        } else if (i == R.id.rl_job_experience_open) {//展开和隐藏工作经历
            if (!isOpenJobExperience) {
                ll_job_experience.setVisibility(View.VISIBLE);
                iv_job_experience.setBackgroundResource(R.drawable.icon_hr_arrow_up);
                isOpenJobExperience = true;
            } else {
                ll_job_experience.setVisibility(View.GONE);
                iv_job_experience.setBackgroundResource(R.drawable.icon_hr_arrow_down);
                isOpenJobExperience = false;
            }
        } else if (i == R.id.rl_education_experience_open) {//展开和隐藏教育经历
            if (!isOpenEducationExperience) {
                ll_education_experience.setVisibility(View.VISIBLE);
                iv_education_experience.setBackgroundResource(R.drawable.icon_hr_arrow_up);
                isOpenEducationExperience = true;
            } else {
                ll_education_experience.setVisibility(View.GONE);
                iv_education_experience.setBackgroundResource(R.drawable.icon_hr_arrow_down);
                isOpenEducationExperience = false;
            }
        } else if (i == R.id.rl_project_experience_open) {//展开和隐藏项目经验
            if (!isOpenProjectExperience) {
                ll_project_experience.setVisibility(View.VISIBLE);
                iv_project_experience.setBackgroundResource(R.drawable.icon_hr_arrow_up);
                isOpenProjectExperience = true;
            } else {
                ll_project_experience.setVisibility(View.GONE);
                iv_project_experience.setBackgroundResource(R.drawable.icon_hr_arrow_down);
                isOpenProjectExperience = false;
            }
        }
    }
}
