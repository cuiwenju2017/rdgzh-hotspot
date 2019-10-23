package com.shanjing.hr.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.StatusBarUtil;
import com.base.commonlib.zxing.android.CaptureActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.fragment.HRCartFragment;
import com.shanjing.hr.fragment.HRHomeFragment;
import com.shanjing.hr.fragment.HRMyFragment;
import com.shanjing.hr.fragment.HRVideoFragment;

/**
 * 人力资源入口
 */

@Route(path = "/hr/main")

public class HRHomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private ImageView iv_scan, iv_search;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    public RadioGroup rg;
    public LinearLayout ll_sign;
    private RadioButton rb_home;
    private HRHomeFragment hrHomeFragment;
    private HRCartFragment hrCartFragment;
    private HRVideoFragment hrVideoFragment;
    private HRMyFragment hrMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrhome);
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.getStatusBarHeight(HRHomeActivity.this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setStatusBarColor(HRHomeActivity.this, Color.parseColor("#ffffff"));//设置背景颜色
        assignViews();
        initData();
    }

    private void assignViews() {
        iv_scan = findViewById(R.id.iv_scan);
        rg = findViewById(R.id.rg);
        ll_sign = findViewById(R.id.ll_sign);
        rb_home = findViewById(R.id.rb_home);
        iv_search = findViewById(R.id.iv_search);
        rg.setOnCheckedChangeListener(this);
        ll_sign.setOnClickListener(this);
        rb_home.setChecked(true);//设置首页选中
        iv_search.setVisibility(View.GONE);//隐藏搜索按钮
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_sign) {//发布
            startActivity(new Intent(this, HRSignActivity.class));
        }
    }

    private void initData() {
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(HRHomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HRHomeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(HRHomeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb_home) {
            if (hrHomeFragment == null) {
                hrHomeFragment = new HRHomeFragment();
                transaction.add(R.id.fragment_container, hrHomeFragment);
            } else {
                transaction.show(hrHomeFragment);
            }
        } else if (checkedId == R.id.rb_mall) {
            if (hrCartFragment == null) {
                hrCartFragment = new HRCartFragment();
                transaction.add(R.id.fragment_container, hrCartFragment);
            } else {
                transaction.show(hrCartFragment);
            }
        } else if (checkedId == R.id.rb_video) {
            if (hrVideoFragment == null) {
                hrVideoFragment = new HRVideoFragment();
                transaction.add(R.id.fragment_container, hrVideoFragment);
            } else {
                transaction.show(hrVideoFragment);
            }
        } else if (checkedId == R.id.rb_my) {
            if (hrMyFragment == null) {
                hrMyFragment = new HRMyFragment();
                transaction.add(R.id.fragment_container, hrMyFragment);
            } else {
                transaction.show(hrMyFragment);
            }
        }
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (hrHomeFragment != null) {
            transaction.hide(hrHomeFragment);
        }
        if (hrCartFragment != null) {
            transaction.hide(hrCartFragment);
        }
        if (hrVideoFragment != null) {
            transaction.hide(hrVideoFragment);
        }
        if (hrMyFragment != null) {
            transaction.hide(hrMyFragment);
        }
    }

}
