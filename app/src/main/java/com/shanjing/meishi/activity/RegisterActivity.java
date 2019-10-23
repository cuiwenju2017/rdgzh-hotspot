package com.shanjing.meishi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.URLS;
import com.base.commonlib.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shanjing.meishi.R;
import com.shanjing.meishi.bean.BaseBean;
import com.shanjing.meishi.bean.RegisterBean;
import com.shanjing.meishi.utils.CountDownTimerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_time;
    private EditText et_phone, et_phone_code, et_pwd, et_pwd_again;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        tv_time = findViewById(R.id.tv_time);
        et_phone = findViewById(R.id.et_phone);
        btn_register = findViewById(R.id.btn_register);
        et_phone_code = findViewById(R.id.et_phone_code);
        et_pwd_again = findViewById(R.id.et_pwd_again);
        et_pwd = findViewById(R.id.et_pwd);
        tv_time.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String etp = et_pwd.getText().toString();
        String etpa = et_pwd_again.getText().toString();
        String phone = et_phone.getText().toString();
        String code = et_phone_code.getText().toString();
        int i = v.getId();
        if (i == R.id.tv_time) {//获取验证码
            if ("".equals(phone) || phone.length() != 11) {
                ToastUtils.showMessage(this, "请输入正确的手机号码");
            } else {
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_time, 60000, 1000);
                mCountDownTimerUtils.start();

                OkHttpClient client = new OkHttpClient();
                client.newBuilder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS).build();
                final Request request = new Request.Builder()
                        .url(URLS.Host + "/Member/registSmsCode?phone=" + phone + "&platform=0")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Log.i("aaa", "onFailure: 失败" + e);
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
                        Log.i("bbb", "onFailure: 成功" + response.body().string());
                    }
                });

            }
        } else if (i == R.id.btn_register) {//注册
            if (et_phone.length() != 11) {
                ToastUtils.showMessage(this, "请输入正确的手机号码");
            } else if (et_phone_code.length() != 6) {
                ToastUtils.showMessage(this, "请输入正确的短信验证码");
            } else if ("".equals(etp) || "".equals(etpa)) {
                ToastUtils.showMessage(this, "密码或确认密码不能为空");
            } else if (!etp.equals(etpa)) {
                ToastUtils.showMessage(this, "两次输入的密码不一样");
            } else {//发送数据进行注册
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                Gson gson = new Gson();
                HashMap<String, String> requestBody = new HashMap<>();
                requestBody.put("platform", "0");
                requestBody.put("userCode", phone);
                requestBody.put("smsCode", code);
                requestBody.put("passWord", etp);

                final Request request = new Request.Builder()
                        .url(URLS.Host + "/Member/DoLogin")
                        .post(RequestBody.create(mediaType, gson.toJson(requestBody)))
                        .build();
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("aaa", "onFailure: 失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<BaseBean<RegisterBean>>() {
                        }.getType();
                        BaseBean<RegisterBean> registerBean = gson.fromJson(response.body().string(), type);

                        if ("1".equals(registerBean.getStatus())){
                            //获取sharedPreferences对象
                            SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                            //获取editor对象
                            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                            editor.putBoolean("isLogin", false);//注册成功之后保存登录状态下次不用重新登录
                            editor.putString("access_Token", registerBean.getData().getAccess_Token());
                            editor.putString("userCode", registerBean.getData().getUserCode());
                            editor.commit();//提交修改
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Looper.prepare();
                            ToastUtils.showMessage(RegisterActivity.this, "" + registerBean.getErrorMsg());
                            Looper.loop();
                        }

                    }
                });


            }
        }
    }
}
