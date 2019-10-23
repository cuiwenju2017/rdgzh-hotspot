package com.shanjing.hr.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.fragment.HRMyFriendsFragment;
import com.shanjing.hr.fragment.HRSignJoinUsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择联系人
 */
public class HRChooseLinkmanActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrchoose_linkman);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("我的好友");
        titles.add("我关注的");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//我的好友
                fragments.add(new HRMyFriendsFragment());
            } else if (i == 1) {//我关注的好友
                fragments.add(new HRSignJoinUsFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adatper);
        viewPager.setCurrentItem(0);//进入页面显示推荐
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        viewPager = findViewById(R.id.tab_vp);
        tabLayout = findViewById(R.id.xTablayout);
        ll_hr_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
