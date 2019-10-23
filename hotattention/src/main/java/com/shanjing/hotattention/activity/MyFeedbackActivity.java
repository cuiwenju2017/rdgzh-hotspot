package com.shanjing.hotattention.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.photoPicker.ImageLoader;
import com.shanjing.hotattention.photoPicker.ImgSelActivity;
import com.shanjing.hotattention.photoPicker.ImgSelConfig;
import com.shanjing.hotattention.utils.ImageLoaderUtils;
import com.shanjing.hotattention.view.NoScrollGridView;
import com.shanjing.hotattention.adapter.NinePicturesAdapter;
import java.util.List;

/**
 * 我的反馈
 */
public class MyFeedbackActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title_name;
    private LinearLayout ll_back;
    private NinePicturesAdapter ninePicturesAdapter;
    private NoScrollGridView gridview;
    private int REQUEST_CODE = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
        initView();
        initData();
    }

    private void initData() {
        tv_title_name.setText("反馈");
        ninePicturesAdapter = new NinePicturesAdapter(this, 9, new NinePicturesAdapter.OnClickAddListener() {
            @Override
            public void onClickAdd(int positin) {
                choosePhoto();//选择图片
            }
        });
        gridview.setAdapter(ninePicturesAdapter);
    }

    private void initView() {
        tv_title_name = findViewById(R.id.tv_title_name);
        ll_back = findViewById(R.id.ll_back);
        gridview = findViewById(R.id.gridview);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        }
    }

    /**
     * 开启图片选择器
     */
    private void choosePhoto() {
        ImgSelConfig config = new ImgSelConfig.Builder(loader)
                // 是否多选
                .multiSelect(true)
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
                // 最大选择图片数量
                .maxNum(9 - ninePicturesAdapter.getPhotoCount())
                .build();
        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
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
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (ninePicturesAdapter != null) {
                ninePicturesAdapter.addAll(pathList);
            }
        }
    }
}
