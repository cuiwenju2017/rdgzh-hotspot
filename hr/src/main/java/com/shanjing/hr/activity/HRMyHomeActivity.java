package com.shanjing.hr.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.StatusBarUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.FragmentAdapter;
import com.shanjing.hr.fragment.HRCartFragment;
import com.shanjing.hr.fragment.HRHRFragment;
import com.shanjing.hr.fragment.HRRecommendFragment;
import com.shanjing.hr.photoPicker.ImageLoader;
import com.shanjing.hr.photoPicker.ImgSelActivity;
import com.shanjing.hr.photoPicker.ImgSelConfig;
import com.shanjing.hr.utils.HRMyHomePopWindow;
import com.shanjing.hr.utils.ImageLoaderUtils;
import com.shanjing.hr.view.CustomScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人主页
 */
public class HRMyHomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;
    private AppBarLayout appbar;
    private FrameLayout fl_layout;
    private LinearLayout ll_hr_back, ll_menu;
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager vp_my_home;
    private XTabLayout xtl_my_home;
    private ImageView iv_photograph, iv_cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrmy_home);
        initView();
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("动态");
        titles.add("推荐");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {//全部
                fragments.add(new HRRecommendFragment());
            } else if (i == 1) {//动态
                fragments.add(new HRCartFragment());
            } else {//推荐
                fragments.add(new HRCartFragment());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        vp_my_home.setAdapter(adatper);
        vp_my_home.setCurrentItem(0);//进入页面显示推荐
        vp_my_home.setOffscreenPageLimit(3);//缓存页数
        xtl_my_home.setupWithViewPager(vp_my_home);
    }

    private void initView() {
        StatusBarUtil.setTranslucentStatus(this);//隐藏状态栏
        tv_title = findViewById(R.id.tv_title);
        appbar = findViewById(R.id.appbar);
        fl_layout = findViewById(R.id.fl_layout);
        ll_hr_back = findViewById(R.id.ll_hr_back);
        ll_menu = findViewById(R.id.ll_menu);
        vp_my_home = findViewById(R.id.vp_my_home);
        xtl_my_home = findViewById(R.id.xtl_my_home);
        iv_photograph = findViewById(R.id.iv_photograph);
        iv_cover = findViewById(R.id.iv_cover);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        iv_photograph.setOnClickListener(this);
        tv_title.setText("个人主页");
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                    fl_layout.setAlpha(percent);
                    setDarkStatusIcon(false);
                } else {
                    float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                    fl_layout.setAlpha(percent);
                    setDarkStatusIcon(true);
                }
            }
        });
    }

    public void setDarkStatusIcon(boolean bDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));//这里对应的是状态栏的颜色，就是style中colorPrimaryDark的颜色
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (bDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//编辑资料、推荐设置
            new HRMyHomePopWindow(this).showAtBottom(ll_menu);
        } else if (i == R.id.iv_photograph) {//选择封面或拍照
            //去寻找是否已经有了相机的权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                ImgSelConfig config = new ImgSelConfig.Builder(loader)
                        // 是否多选
                        .multiSelect(false)
                        // 确定按钮背景色
                        .btnBgColor(Color.TRANSPARENT)
                        .titleBgColor(ContextCompat.getColor(this, R.color.colorBule))
                        // 使用沉浸式状态栏
                        .statusBarColor(ContextCompat.getColor(this, R.color.colorBule))
                        // 返回图标ResId
                        .backResId(R.drawable.ic_back_x)
                        .title("图片")
                        // 第一个是否显示相机
                        .needCamera(true)
                        //按钮字体颜色
                        .btnTextColor(ContextCompat.getColor(this, R.color.colorBule))
                        .build();
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
            } else {
                //否则去请求相机权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
    }

    private int REQUEST_CODE = 120;

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.display(context, imageView, path);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            Glide.with(this).load(pathList.get(0)).into(iv_cover);//显示图片
        }
    }

}
