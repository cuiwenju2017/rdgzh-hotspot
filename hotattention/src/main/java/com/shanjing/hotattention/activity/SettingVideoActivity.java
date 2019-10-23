package com.shanjing.hotattention.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.ToastUtil;
import com.shanjing.hotattention.R;

/**
 * 设置视频的播放方式
 */
public class SettingVideoActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private RadioGroup rg;
    private RadioButton rb_all, rb_wifi, rb_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_video);
        initView();
        initData();
    }

    private void initData() {
        tv_title_name.setText("设置");
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rg = findViewById(R.id.rg);
        rb_all = findViewById(R.id.rb_all);
        rb_wifi = findViewById(R.id.rb_wifi);
        rb_close = findViewById(R.id.rb_close);
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

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int i = checkedId;
        if (i == R.id.rb_all) {
            sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("rb", "移动流量和WIFI环境下自动播放");
            editorMain.commit();
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        } else if (i == R.id.rb_wifi) {
            sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("rb", "仅WIFI环境下自动播放");
            editorMain.commit();
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        } else if (i == R.id.rb_close) {
            sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("rb", "关闭");
            editorMain.commit();
            startActivity(new Intent(this, SettingActivity.class));
            finish();
        }
    }
}
