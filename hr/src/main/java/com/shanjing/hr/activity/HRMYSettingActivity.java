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
 * 设置
 */
public class HRMYSettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private RelativeLayout rl_account_binding, rl_inform_remind, rl_privacy_settings, rl_say_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrmysetting);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        rl_account_binding = findViewById(R.id.rl_account_binding);
        rl_inform_remind = findViewById(R.id.rl_inform_remind);
        rl_privacy_settings = findViewById(R.id.rl_privacy_settings);
        rl_say_hello = findViewById(R.id.rl_say_hello);
        ll_hr_back.setOnClickListener(this);
        rl_account_binding.setOnClickListener(this);
        rl_inform_remind.setOnClickListener(this);
        rl_privacy_settings.setOnClickListener(this);
        rl_say_hello.setOnClickListener(this);
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
        } else if (i == R.id.rl_inform_remind) {//通知与提醒
            startActivity(new Intent(this, HRInformRemindActivity.class));
        } else if (i == R.id.rl_privacy_settings) {//隐私设置
            startActivity(new Intent(this, HRPrivacySettingsActivity.class));
        } else if (i == R.id.rl_say_hello) {//打招呼语
            startActivity(new Intent(this, HRSayHelloActivity.class));
        }
    }
}
