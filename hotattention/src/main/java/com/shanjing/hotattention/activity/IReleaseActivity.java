package com.shanjing.hotattention.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.FragmentAdapter;
import com.shanjing.hotattention.fragment.MyAllFragment;
import com.shanjing.hotattention.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的发布
 */
public class IReleaseActivity extends BaseActivity implements View.OnClickListener {

    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irelease);
        initView();
        initViewPager();
    }

    private void initView() {
        viewPager = findViewById(R.id.tab_vp);
        tabLayout = findViewById(R.id.xTablayout);
        ll_back = findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("热点");
        titles.add("问答");
        titles.add("帮忙");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new MyAllFragment());
            } else {
                fragments.add(new TabFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }
}
