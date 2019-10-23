package com.shanjing.hotattention.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.StatusBarUtil;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.zxing.android.CaptureActivity;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.fragment.HotHomeFragment;
import com.shanjing.hotattention.fragment.HotMallFragment;
import com.shanjing.hotattention.fragment.HotMyFragment;
import com.shanjing.hotattention.fragment.HotVideoFragment;
import com.shanjing.hotattention.utils.AddFriendPopWindow;
import com.shanjing.hotattention.utils.IssuePopWindow;

import java.io.ByteArrayOutputStream;

/**
 * 热点关注首页
 */
@Route(path = "/hotattention/main")
public class HotAttentionHomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private ImageView iv_scan, iv_search, iv;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    byte[] result;
    public RadioGroup rg;
    public LinearLayout ll_sign;
    private RadioButton rb_home, rb_mall, rb_video, rb_my;
    private HotHomeFragment hotHomeFragment;
    private HotMallFragment hotMallFragment;
    private HotVideoFragment hotVideoFragment;
    private HotMyFragment hotMyFragment;

    private void assignViews() {
        iv_scan = findViewById(R.id.iv_scan);
        rg = findViewById(R.id.rg);
        ll_sign = findViewById(R.id.ll_sign);
        rb_home = findViewById(R.id.rb_home);
        rb_mall = findViewById(R.id.rb_mall);
        rb_video = findViewById(R.id.rb_video);
        rb_my = findViewById(R.id.rb_my);
        iv_search = findViewById(R.id.iv_search);
        iv = findViewById(R.id.iv);
        rg.setOnCheckedChangeListener(this);
        ll_sign.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        rb_home.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_attention_home);
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.getStatusBarHeight(HotAttentionHomeActivity.this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setStatusBarColor(HotAttentionHomeActivity.this, Color.parseColor("#ffffff"));//设置背景颜色
        assignViews();
        initData();
    }

    private void initData() {
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(HotAttentionHomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HotAttentionHomeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();

                ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                result = output.toByteArray();
                Intent intent = new Intent();
                intent.setClass(HotAttentionHomeActivity.this, Zxingbimtp.class);
                intent.putExtra("bitmap", content);
                startActivity(intent);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(HotAttentionHomeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb_home) {
            if (hotHomeFragment == null) {
                hotHomeFragment = new HotHomeFragment();
                transaction.add(R.id.fragment_container, hotHomeFragment);
            } else {
                transaction.show(hotHomeFragment);
            }
        } else if (checkedId == R.id.rb_mall) {
            if (hotMallFragment == null) {
                hotMallFragment = new HotMallFragment();
                transaction.add(R.id.fragment_container, hotMallFragment);
            } else {
                transaction.show(hotMallFragment);
            }
        } else if (checkedId == R.id.rb_video) {
            if (hotVideoFragment == null) {
                hotVideoFragment = new HotVideoFragment();
                transaction.add(R.id.fragment_container, hotVideoFragment);
            } else {
                transaction.show(hotVideoFragment);
            }
        } else if (checkedId == R.id.rb_my) {
            if (hotMyFragment == null) {
                hotMyFragment = new HotMyFragment();
                transaction.add(R.id.fragment_container, hotMyFragment);
            } else {
                transaction.show(hotMyFragment);
            }
        }
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (hotHomeFragment != null) {
            transaction.hide(hotHomeFragment);
        }
        if (hotMallFragment != null) {
            transaction.hide(hotMallFragment);
        }
        if (hotVideoFragment != null) {
            transaction.hide(hotVideoFragment);
        }
        if (hotMyFragment != null) {
            transaction.hide(hotMyFragment);
        }
    }

    private boolean isPop;
    private IssuePopWindow issuePopWindow;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_sign) {//弹窗选择发布分类
            // 旋转
            RotateAnimation rAnim = new RotateAnimation(0, 45,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rAnim.setDuration(3 * 100);
            //动画完成后，是否保持
            rAnim.setFillAfter(true);
            iv.startAnimation(rAnim);
            issuePopWindow = new IssuePopWindow(this,iv);
            issuePopWindow.showAtBottom(ll_sign);
        } else if (i == R.id.iv_search) {//搜索
            startActivity(new Intent(this, SearchActivity.class));
        }
    }
}
