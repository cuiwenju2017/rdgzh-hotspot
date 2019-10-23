package com.shanjing.hotattention.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点关注视频
 */
public class HotVideoFragment extends BaseFragment {

    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_hot_video;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.tab_vp);
        tabLayout = view.findViewById(R.id.xTablayout);
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("关注");
        titles.add("推荐");
        titles.add("抖音");
        titles.add("快手");
        titles.add("西瓜");
        titles.add("火山");
        titles.add("梨视频");
        titles.add("好看");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//关注
                fragments.add(new AttentionFragment());
            } else if (i == 1) {//推荐
                fragments.add(new RecommendFragment());
            } else {
                fragments.add(new TabFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void fetchData() {
        Log.d("ssss", "333---------*********/////////");

    }

}
