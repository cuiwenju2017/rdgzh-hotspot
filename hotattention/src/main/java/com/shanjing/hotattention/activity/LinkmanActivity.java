package com.shanjing.hotattention.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.FragmentAdapter;
import com.shanjing.hotattention.fragment.FriendFragment;
import com.shanjing.hotattention.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 */
public class LinkmanActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkman);
        initView();
        initData();
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("好友");
        titles.add("我的关注");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new FriendFragment());
            } else {
                fragments.add(new TabFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        ViewPager viewPager = findViewById(R.id.tab_vp);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        final XTabLayout tabLayout = findViewById(R.id.xTablayout);
        //给TabLayout设置适配器
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        tv_title_name.setText("联系人");
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//退出本页
            finish();
        }
    }
}
