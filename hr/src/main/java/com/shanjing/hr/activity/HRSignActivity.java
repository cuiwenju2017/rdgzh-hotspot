package com.shanjing.hr.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.fragment.HRSignDynamicFragment;
import com.shanjing.hr.fragment.HRSignJobWantedFragment;
import com.shanjing.hr.fragment.HRSignJoinUsFragment;
import com.shanjing.hr.fragment.HRSignResumeFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布
 */
public class HRSignActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_search;
    private RelativeLayout rl_back;
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrsign);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("动态");
        titles.add("招聘");
        titles.add("求职");
        titles.add("简历");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//发布动态
                fragments.add(new HRSignDynamicFragment());
            } else if (i == 1) {//发布招聘
                fragments.add(new HRSignJoinUsFragment());
            } else if (i == 2) {//发布求职
                fragments.add(new HRSignJobWantedFragment());
            } else if (i == 3) {//发布简历
                fragments.add(new HRSignResumeFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adatper);
        viewPager.setCurrentItem(0);//进入页面显示推荐
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        iv_search = findViewById(R.id.iv_search);
        rl_back = findViewById(R.id.rl_back);
        viewPager = findViewById(R.id.tab_vp);
        tabLayout = findViewById(R.id.xTablayout);
        rl_back.setOnClickListener(this);
        iv_search.setVisibility(View.GONE);//隐藏搜索图标
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_back) {
            finish();
        }
    }
}
