package com.shanjing.hotattention.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;

/**
 * 隐私设置
 */
public class PrivacySettingActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private RadioButton rb_open, rb_perpetual, rb_some_open;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_setting);
        initView();
        initData();
    }

    private void initData() {
        tv_title_name.setText("隐私设置");
        rb_open.setChecked(true);
        rb_perpetual.setChecked(true);
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rb_open = findViewById(R.id.rb_open);
        rb_perpetual = findViewById(R.id.rb_perpetual);
        rb_some_open = findViewById(R.id.rb_some_open);
        rg = findViewById(R.id.rg);
        ll_back.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int i = checkedId;
        if (i == R.id.rb_some_open) {
            Toast.makeText(this, "仅部分人可见", Toast.LENGTH_SHORT).show();
        }
    }
}
