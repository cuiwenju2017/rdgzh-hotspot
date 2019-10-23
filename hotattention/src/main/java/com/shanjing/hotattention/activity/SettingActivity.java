package com.shanjing.hotattention.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;

/**
 * 我的设置
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name, tv_version_name, tv_text;
    private RelativeLayout rl_setting_video, rl_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
    }

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;

    private void initData() {
        tv_title_name.setText("设置");
        tv_version_name.setText("当前版本：" + getLocalVersionName(this));
        sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
        String video = sprfMain.getString("rb", "");
        tv_text.setText(video);
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        tv_version_name = findViewById(R.id.tv_version_name);
        rl_setting_video = findViewById(R.id.rl_setting_video);
        tv_text = findViewById(R.id.tv_text);
        rl_privacy = findViewById(R.id.rl_privacy);
        ll_back.setOnClickListener(this);
        rl_setting_video.setOnClickListener(this);
        rl_privacy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        } else if (i == R.id.rl_setting_video) {//设置视频播放方式
            startActivity(new Intent(this, SettingVideoActivity.class));
            finish();
        } else if (i == R.id.rl_privacy) {//评论隐私设置
            startActivity(new Intent(this, PrivacySettingActivity.class));
        }
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

}
