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
import com.shanjing.hr.fragment.HRCollectCompanyFragment;
import com.shanjing.hr.fragment.HRForTheInterviewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心收藏
 */
public class HRCollectActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager vp_my_collect;
    private XTabLayout xtl_my_collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrcollect);
        initView();
        initViewPager();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        vp_my_collect = findViewById(R.id.vp_my_collect);
        xtl_my_collect = findViewById(R.id.xtl_my_collect);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("收藏");
        ll_menu.setVisibility(View.GONE);
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("公司");
        titles.add("职位");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//公司
                fragments.add(new HRCollectCompanyFragment());
            } else {//职位
                fragments.add(new HRCollectCompanyFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        vp_my_collect.setAdapter(adatper);
        vp_my_collect.setCurrentItem(0);//进入页面显示
        vp_my_collect.setOffscreenPageLimit(2);//缓存页数
        xtl_my_collect.setupWithViewPager(vp_my_collect);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
