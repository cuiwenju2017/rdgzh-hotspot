package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 不合适回复语
 */
public class HRCompanySayNoActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private RadioButton rb3;
    private Switch sc;
    private RadioGroup rg;
    private Button bnt_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrcompany_say_no);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        rb3 = findViewById(R.id.rb3);
        sc = findViewById(R.id.sc);
        rg = findViewById(R.id.rg);
        bnt_custom = findViewById(R.id.bnt_custom);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("不合适回复语");
        ll_menu.setVisibility(View.GONE);
        rb3.setChecked(true);
        sc.setChecked(true);
        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rg.setVisibility(View.VISIBLE);
                    bnt_custom.setVisibility(View.VISIBLE);
                } else {
                    rg.setVisibility(View.GONE);
                    bnt_custom.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }

}
