package com.shanjing.hr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.view.CustomScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心管理
 */
public class HRMyManagementFragment extends Fragment {

    List<Fragment> fragments = new ArrayList<>();
    private XTabLayout xtl_manage;
    private ViewPager vp_manage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrmy_management, container, false);
        initView(view);
        initViewPager();
        return view;
    }

    private void initView(View view) {
        vp_manage = view.findViewById(R.id.vp_manage);
        xtl_manage = view.findViewById(R.id.xtl_manage);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("求职");
        titles.add("招聘");
        titles.add("平台");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//求职
                fragments.add(new HRMyWantedJobFragment());
            } else if (i == 1) {//招聘
                fragments.add(new HRMyGiveJobragment());
            } else {//平台
                fragments.add(new HRCartFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        vp_manage.setAdapter(adatper);
        vp_manage.setCurrentItem(0);//进入页面显示推荐
        vp_manage.setOffscreenPageLimit(3);//缓存页数
        xtl_manage.setupWithViewPager(vp_manage);
    }

}
