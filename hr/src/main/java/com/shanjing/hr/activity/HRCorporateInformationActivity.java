package com.shanjing.hr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.FirstEvent;
import com.shanjing.hr.bean.HRCompanyNameEvent;
import com.shanjing.hr.bean.HRCompanyPhoneEvent;
import com.shanjing.hr.bean.HRFinancingEvent;
import com.shanjing.hr.bean.HRScaleEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 公司基本信息
 */
public class HRCorporateInformationActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_name, tv_company_phone, tv_financing, tv_scale;
    private ImageView iv;
    private RelativeLayout rl_company_name, rl_company_address, rl_company_phone,
            rl_business_license, rl_financing, rl_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_hrcorporate_information);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_company_name = findViewById(R.id.rl_company_name);
        tv_name = findViewById(R.id.tv_name);
        rl_company_address = findViewById(R.id.rl_company_address);
        rl_company_phone = findViewById(R.id.rl_company_phone);
        tv_company_phone = findViewById(R.id.tv_company_phone);
        rl_business_license = findViewById(R.id.rl_business_license);
        rl_financing = findViewById(R.id.rl_financing);
        tv_financing = findViewById(R.id.tv_financing);
        rl_scale = findViewById(R.id.rl_scale);
        tv_scale = findViewById(R.id.tv_scale);
        ll_hr_back.setOnClickListener(this);
        rl_company_name.setOnClickListener(this);
        rl_company_address.setOnClickListener(this);
        rl_company_phone.setOnClickListener(this);
        rl_business_license.setOnClickListener(this);
        rl_financing.setOnClickListener(this);
        rl_scale.setOnClickListener(this);
        tv_title.setText("公司基本信息");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    @Subscribe
    public void onEventMainThread(HRCompanyNameEvent event) {//公司名称获取
        tv_name.setTextColor(getResources().getColor(R.color.color_text));
        tv_name.setText(event.getMessage());
    }

    @Subscribe
    public void onEventMainThread(HRCompanyPhoneEvent phoneEvent) {//公司电话获取
        tv_company_phone.setTextColor(getResources().getColor(R.color.color_text));
        tv_company_phone.setText(phoneEvent.getMessage());
    }

    @Subscribe
    public void onEventMainThread(HRFinancingEvent financingEvent) {//融资状况获取
        tv_financing.setTextColor(getResources().getColor(R.color.color_text));
        tv_financing.setText(financingEvent.getMessage());
    }

    @Subscribe
    public void onEventMainThread(HRScaleEvent financingEvent) {//获取公司规模并显示
        tv_scale.setTextColor(getResources().getColor(R.color.color_text));
        tv_scale.setText(financingEvent.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_company_name) {//公司名称
            startActivity(new Intent(this, HRCompanyNameActivity.class));
        } else if (i == R.id.rl_company_address) {//公司地址
            startActivity(new Intent(this, HRCompanyAddressActivity.class));
        } else if (i == R.id.rl_company_phone) {//公司电话
            startActivity(new Intent(this, HRCompanyPhoneActivity.class));
        } else if (i == R.id.rl_business_license) {//营业执照
            startActivity(new Intent(this, HRBusinessLicenseActivity.class));
        } else if (i == R.id.rl_financing) {//融资状况
            startActivity(new Intent(this, HRFinancingActivity.class));
        } else if (i == R.id.rl_scale) {//公司规模
            startActivity(new Intent(this, HRScaleActivity.class));
        }
    }
}
