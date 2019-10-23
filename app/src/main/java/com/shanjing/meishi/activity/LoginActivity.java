package com.shanjing.meishi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.URLS;
import com.base.commonlib.utils.ToastUtils;
import com.base.commonlib.view.LoadingDialog;
import com.shanjing.meishi.R;
import com.shanjing.meishi.utils.AniDialog;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button login_denlu;
    private EditText login_edit_account;
    private EditText login_edit_pwd;
    private TextView tv_register;
    private AniDialog aniDialog;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadingDialog = new LoadingDialog(this);
        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", true);
        if (!isLogin) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            initView();
            aniDialog = new AniDialog(this, null);
        }
    }

    private void initView() {
        login_denlu = findViewById(R.id.login_denlu);
        tv_register = findViewById(R.id.tv_register);
        login_denlu.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        //账号
        login_edit_account = (EditText) findViewById(R.id.login_edit_account);
        //密码
        login_edit_pwd = (EditText) findViewById(R.id.login_edit_pwd);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.login_denlu) {
            getlogin();
        } else if (i == R.id.tv_register) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void getlogin() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
