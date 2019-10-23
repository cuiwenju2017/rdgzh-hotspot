package com.shanjing.hr.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.fragment.HRCartFragment;
import com.shanjing.hr.fragment.HRForTheInterviewFragment;
import com.shanjing.hr.fragment.HRHRFragment;
import com.shanjing.hr.view.CustomScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心我的面试
 */
public class HRMyInterviewActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager vp_my_interview;
    private XTabLayout xtl_my_interview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrmy_interview);
        initView();
        initViewPager();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        vp_my_interview = findViewById(R.id.vp_my_interview);
        xtl_my_interview = findViewById(R.id.xtl_my_interview);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("我的面试");
        ll_menu.setVisibility(View.GONE);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("待面试");
        titles.add("已面试");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//待面试
                fragments.add(new HRForTheInterviewFragment());
            } else {//已面试
                fragments.add(new HRForTheInterviewFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        vp_my_interview.setAdapter(adatper);
        vp_my_interview.setCurrentItem(0);//进入页面显示
        vp_my_interview.setOffscreenPageLimit(2);//缓存页数
        xtl_my_interview.setupWithViewPager(vp_my_interview);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
