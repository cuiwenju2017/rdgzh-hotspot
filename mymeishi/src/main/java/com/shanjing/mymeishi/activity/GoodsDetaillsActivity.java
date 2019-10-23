package com.shanjing.mymeishi.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.fragment.CommentFragment;
import com.shanjing.mymeishi.fragment.Goosxiangqsp;
import com.shanjing.mymeishi.fragment.ParticularsFragment;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetaillsActivity extends AppCompatActivity {
    private String goodsid;
    private TabLayout goodstabLayout;
    private ViewPager goodstab_vp;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private Goosxiangqsp goosxiangqsp;
    private CommentFragment commentFragment;
    private ParticularsFragment particularsFragment;
    private int mid;
    private TextView tv_title;
    private RelativeLayout rl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detaills);
        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsID");//商品id
        initView();
    }

    private void initView() {
        rl_back =(RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title =(TextView) findViewById(R.id.tv_title);
        tv_title.setText("商品详情");
        goodstabLayout = (TabLayout) findViewById(R.id.goodstabLayout);
        goodstab_vp = (ViewPager) findViewById(R.id.goodstab_vp);
        goodstabLayout = (TabLayout) findViewById(R.id.goodstabLayout);
        goodstab_vp = (ViewPager) findViewById(R.id.goodstab_vp);
        titleList = new ArrayList<>();
        titleList.add("商品");
        titleList.add("评价");
        titleList.add("详情");
        fragmentList = new ArrayList<>();
        goosxiangqsp = new Goosxiangqsp(goodsid);
        commentFragment = new CommentFragment(goodsid);
        particularsFragment = new ParticularsFragment(goodsid);
        fragmentList.add(goosxiangqsp);//商品
        fragmentList.add(commentFragment);//评价
        fragmentList.add(particularsFragment);//详情
        goodstab_vp.setAdapter(new MyFragmentAdpter(this.getSupportFragmentManager()));
        goodstabLayout.setupWithViewPager(goodstab_vp);
        goodstabLayout.setTabMode(TabLayout.MODE_FIXED);
        goodstabLayout.setTabRippleColor(ColorStateList.valueOf(GoodsDetaillsActivity.this.getResources().getColor(R.color.colorwaite)));


    }

    public class MyFragmentAdpter extends FragmentPagerAdapter {

        public MyFragmentAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);//页卡标题
        }
    }
}
