package com.shanjing.meishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.commonlib.BaseActivity;
import com.shanjing.meishi.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn, btn_hot_attention, btn_hr;
    private long mExitToastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //获取sharedPreferences对象
       /* SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("access_Token", "");
        String uc = sharedPreferences.getString("userCode", "");*/
    }

    private void initView() {
        btn = findViewById(R.id.btn);
        btn_hot_attention = findViewById(R.id.btn_hot_attention);
        btn_hr = findViewById(R.id.btn_hr);
        btn.setOnClickListener(this);
        btn_hot_attention.setOnClickListener(this);
        btn_hr.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //点击两次退出
    public void exit() {
        if ((System.currentTimeMillis() - mExitToastTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitToastTime = System.currentTimeMillis();
        } else {
            Intent MyIntent = new Intent(Intent.ACTION_MAIN);
            MyIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(MyIntent);
            finish();
            System.exit(0);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ARouter.getInstance().build("/mymeishi/main").navigation();
                break;
            case R.id.btn_hot_attention:
                ARouter.getInstance().build("/hotattention/main").navigation();
                break;
            case R.id.btn_hr:
                ARouter.getInstance().build("/hr/main").navigation();
                break;
            default:
                break;
        }
    }
}
