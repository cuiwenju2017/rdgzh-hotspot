package com.shanjing.hotattention.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.FragmentAdapter;
import com.shanjing.hotattention.fragment.MyCollectFragment;
import com.shanjing.hotattention.fragment.MyCommentFragment;
import com.shanjing.hotattention.fragment.MyLikeFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 */
public class MyCollectActivity extends BaseActivity implements View.OnClickListener {

    List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        initView();
        initViewPager();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("flag2", 0);
        if (id > 0) {
            //fragment的切换采用的是viewpage的形式,然后1是指底部第2个Fragment
            viewPager.setCurrentItem(1);
        }
        int id3 = intent.getIntExtra("flag3", 0);
        if (id3 > 0) {
            //fragment的切换采用的是viewpage的形式,然后2是指底部第3个Fragment
            viewPager.setCurrentItem(2);
        }
    }

    private void initView() {
        viewPager = findViewById(R.id.tab_vp);
        tabLayout = findViewById(R.id.xTablayout);
        ll_back = findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("收藏");
        titles.add("点赞");
        titles.add("评论");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//我的收藏
                fragments.add(new MyCollectFragment());
            } else if (i == 1) {//我的点赞
                fragments.add(new MyLikeFragment());
            } else if (i == 2) {
                fragments.add(new MyCommentFragment());
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
