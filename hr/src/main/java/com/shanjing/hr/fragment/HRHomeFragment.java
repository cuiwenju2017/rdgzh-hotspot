package com.shanjing.hr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidkun.xtablayout.XTabLayout;
import com.shanjing.hr.R;
import com.shanjing.hr.activity.ChooseCityActivity;
import com.shanjing.hr.activity.SearchActivity;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

/**
 * 人力资源首页
 */
public class HRHomeFragment extends Fragment implements View.OnClickListener {

    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private TextView tv_city;
    private LinearLayout ll_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //注册EventBus
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_hrhome, container, false);
        //获取控件
        viewPager = view.findViewById(R.id.tab_vp);
        tabLayout = view.findViewById(R.id.xTablayout);
        tv_city = view.findViewById(R.id.tv_city);
        ll_search = view.findViewById(R.id.ll_search);
        //获取监听
        tv_city.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        initViewPager();
        return view;
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("关注");
        titles.add("推荐");
        titles.add("求职");
        titles.add("招聘");
        titles.add("平台");
        titles.add("简历");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//关注
                fragments.add(new HRAttentionFragment());
            } else if (i == 1) {//推荐
                fragments.add(new HRRecommendFragment());
            } else if (i == 2) {//求职
                fragments.add(new JobWantedFragment());
            } else if (i == 3) {//招聘
                fragments.add(new GiveJobFragment());
            } else if (i == 4) {//平台
                fragments.add(new PlatformFragment());
            } else if (i == 5) {//简历
                fragments.add(new HRResumeFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adatper);
        viewPager.setCurrentItem(1);//进入页面显示推荐
        tabLayout.setupWithViewPager(viewPager);
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        tv_city.setText(event.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_city) {//点击跳转到选择城市页面
            startActivity(new Intent(getActivity(), ChooseCityActivity.class));
        } else if (i == R.id.ll_search) {//跳转到搜索页面
            startActivity(new Intent(getActivity(), SearchActivity.class));
        }
    }
}
