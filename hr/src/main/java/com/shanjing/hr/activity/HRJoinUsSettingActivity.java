package com.shanjing.hr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 招聘设置
 */
public class HRJoinUsSettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private RelativeLayout rl_account_binding, rl_company_inform_remind, rl_company_say_hello,
            rl_company_say_no, rl_about, rl_help_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrjoin_us_setting);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        rl_account_binding = findViewById(R.id.rl_account_binding);
        rl_company_inform_remind = findViewById(R.id.rl_company_inform_remind);
        rl_company_say_hello = findViewById(R.id.rl_company_say_hello);
        rl_company_say_no = findViewById(R.id.rl_company_say_no);
        rl_about = findViewById(R.id.rl_about);
        rl_help_feedback = findViewById(R.id.rl_help_feedback);
        ll_hr_back.setOnClickListener(this);
        rl_account_binding.setOnClickListener(this);
        rl_company_inform_remind.setOnClickListener(this);
        rl_company_say_hello.setOnClickListener(this);
        rl_company_say_no.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        rl_help_feedback.setOnClickListener(this);
        tv_title.setText("设置");
        ll_menu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_account_binding) {//账号与绑定
            startActivity(new Intent(this, HRAccountBindingActivity.class));
        } else if (i == R.id.rl_company_inform_remind) {//招聘通知与提醒
            startActivity(new Intent(this, HRCompanyInformRemindActivity.class));
        } else if (i == R.id.rl_company_say_hello) {//打招呼语
            startActivity(new Intent(this, HRCompanySayHelloActivity.class));
        } else if (i == R.id.rl_company_say_no) {//不合适回复语
            startActivity(new Intent(this, HRCompanySayNoActivity.class));
        } else if (i == R.id.rl_about) {//关于
            startActivity(new Intent(this, HRAboutActivity.class));
        } else if (i == R.id.rl_help_feedback) {//帮助与反馈
            startActivity(new Intent(this, HRHelpFeedbackActivity.class));
        }
    }
}
