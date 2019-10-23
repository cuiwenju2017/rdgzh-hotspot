package com.shanjing.hr.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.bumptech.glide.Glide;
import com.shanjing.hr.R;
import com.shanjing.hr.photoPicker.ImageLoader;
import com.shanjing.hr.photoPicker.ImgSelActivity;
import com.shanjing.hr.photoPicker.ImgSelConfig;
import com.shanjing.hr.utils.ImageLoaderUtils;

import java.util.List;

/**
 * 营业执照
 */
public class HRBusinessLicenseActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_upload_hrbusiness;
    private ImageView iv, iv_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrbusiness_license);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        tv_upload_hrbusiness = findViewById(R.id.tv_upload_hrbusiness);
        iv_business = findViewById(R.id.iv_business);
        ll_hr_back.setOnClickListener(this);
        tv_upload_hrbusiness.setOnClickListener(this);
        tv_title.setText("营业执照");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    private int REQUEST_CODE = 120;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.tv_upload_hrbusiness) {//选择或拍照营业执照
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
            iv_business.setVisibility(View.VISIBLE);
            tv_upload_hrbusiness.setText("重新上传");
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            Glide.with(this).load(pathList.get(0)).into(iv_business);//显示图片
        }
    }

}
