package com.shanjing.hr.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRLocationActivity;
import com.shanjing.hr.activity.HRSignJurisdictionActivity;
import com.shanjing.hr.adapter.HRSyncAdapter;
import com.shanjing.hr.adapter.NinePicturesAdapter;
import com.shanjing.hr.photoPicker.ImageLoader;
import com.shanjing.hr.photoPicker.ImgSelActivity;
import com.shanjing.hr.photoPicker.ImgSelConfig;
import com.shanjing.hr.utils.ImageLoaderUtils;
import com.shanjing.hr.view.NoScrollGridView;
import java.util.Arrays;
import java.util.List;

/**
 * 发布动态
 */
public class HRSignDynamicFragment extends Fragment implements View.OnClickListener {

    private NoScrollGridView gridview;
    private NinePicturesAdapter ninePicturesAdapter;
    private Dialog dialog;
    private int REQUEST_CODE = 120;
    private RecyclerView rv_sync;
    private LinearLayout ll_recommendation;
    private ImageView iv_recommendation;
    private TextView tv_rule;
    private RelativeLayout rl_location, rl_jurisdiction_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrsign_dynamic, container, false);
        gridview = view.findViewById(R.id.gridview);
        rv_sync = view.findViewById(R.id.rv_sync);
        ll_recommendation = view.findViewById(R.id.ll_recommendation);
        iv_recommendation = view.findViewById(R.id.iv_recommendation);
        rl_location = view.findViewById(R.id.rl_location);
        tv_rule = view.findViewById(R.id.tv_rule);
        rl_jurisdiction_setting = view.findViewById(R.id.rl_jurisdiction_setting);
        ll_recommendation.setOnClickListener(this);
        rl_location.setOnClickListener(this);
        rl_jurisdiction_setting.setOnClickListener(this);
        initView();
        return view;
    }

    private List<String> list = Arrays.asList("58招聘", "51job", "BOSS直聘", "智联招聘", "中华英才");

    private void initView() {
        tv_rule.setText(Html.fromHtml("发布职位即表示同意遵守<font color='#FF0000'>《人力资源职位信息发布规则》</font>，如违反规定可能导致您的账号被锁定。"));
        //选择图片适配器
        ninePicturesAdapter = new NinePicturesAdapter(getActivity(), 9, new NinePicturesAdapter.OnClickAddListener() {
            @Override
            public void onClickAdd(int positin) {
                dialogMedia();
            }
        });
        gridview.setAdapter(ninePicturesAdapter);

        //同步到各平台的适配器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//布局横向
        rv_sync.setLayoutManager(linearLayoutManager);
        HRSyncAdapter hrSymcAdapter = new HRSyncAdapter(getActivity(), list);
        rv_sync.setAdapter(hrSymcAdapter);
    }

    private void dialogMedia() {
        dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_media, null);
        //获取组件
        TextView tv_picture = contentView.findViewById(R.id.tv_picture);
        TextView tv_video = contentView.findViewById(R.id.tv_video);
        //获取Dialog的监听
        tv_picture.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().densityDpi;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);//弹窗位置
        dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
        dialog.show();//显示弹窗
    }

    private boolean isRecommendation = false;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_picture) {
            choosePhoto();//选择图片
        } else if (i == R.id.ll_recommendation) {//点击是否选择推荐
            if (!isRecommendation) {
                iv_recommendation.setBackgroundResource(R.drawable.check_hr_on_recommendation);
                isRecommendation = true;
            } else {
                iv_recommendation.setBackgroundResource(R.drawable.check_hr_off_recommendation);
                isRecommendation = false;
            }
        } else if (i == R.id.rl_location) {//选择位置
            startActivity(new Intent(getActivity(), HRLocationActivity.class));
        } else if (i == R.id.rl_jurisdiction_setting) {//跳转到权限设置页
            startActivity(new Intent(getActivity(), HRSignJurisdictionActivity.class));
        }
    }

    /**
     * 开启图片选择器
     */
    private void choosePhoto() {
        //去寻找是否已经有了相机的权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            dialog.dismiss();
            ImgSelConfig config = new ImgSelConfig.Builder(loader)
                    // 是否多选
                    .multiSelect(true)
                    // 确定按钮背景色
                    .btnBgColor(Color.TRANSPARENT)
                    .titleBgColor(ContextCompat.getColor(getActivity(), R.color.colorBule))
                    // 使用沉浸式状态栏
                    .statusBarColor(ContextCompat.getColor(getActivity(), R.color.colorBule))
                    // 返回图标ResId

                    .backResId(R.drawable.ic_back_x)
                    .title("图片")
                    // 第一个是否显示相机
                    .needCamera(true)
                    // 最大选择图片数量
                    .maxNum(18 - ninePicturesAdapter.getPhotoCount())
                    .build();
            ImgSelActivity.startActivity(this, config, REQUEST_CODE);

        } else {
            //否则去请求相机权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }

    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.display(context, imageView, path);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (ninePicturesAdapter != null) {
                ninePicturesAdapter.addAll(pathList);
            }
        }
    }

}
