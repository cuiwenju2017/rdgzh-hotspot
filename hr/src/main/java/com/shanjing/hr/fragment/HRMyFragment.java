package com.shanjing.hr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRMyHomeActivity;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.view.CustomScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 人力资源我的
 */
public class HRMyFragment extends Fragment implements View.OnClickListener {

    List<Fragment> fragments = new ArrayList<>();
    private CustomScrollViewPager vp_my;
    private XTabLayout xtl_my;
    private LinearLayout ll_my_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrmy, container, false);
        initView(view);
        initViewPager();
        return view;
    }

    private void initView(View view) {
        vp_my = view.findViewById(R.id.vp_my);
        xtl_my = view.findViewById(R.id.xtl_my);
        ll_my_home = view.findViewById(R.id.ll_my_home);
        ll_my_home.setOnClickListener(this);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("美食");
        titles.add("热点关注");
        titles.add("人力资源");
        titles.add("咖啡馆");
        titles.add("黄牛馆");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//美食
                fragments.add(new HRCartFragment());
            } else if (i == 1) {//热点关注
                fragments.add(new HRCartFragment());
            } else if (i == 2) {//人力资源
                fragments.add(new HRHRFragment());
            } else {
                fragments.add(new HRCartFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        vp_my.setAdapter(adatper);
        vp_my.setCurrentItem(2);//进入页面显示推荐
        vp_my.setOffscreenPageLimit(3);//缓存页数
        xtl_my.setupWithViewPager(vp_my);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_my_home) {//个人主页
            startActivity(new Intent(getActivity(), HRMyHomeActivity.class));
        }
    }
}
