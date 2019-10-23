package com.shanjing.hr.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRMyEditDataActivity;
import com.shanjing.hr.activity.HRSignJurisdictionActivity;

/**
 * 个人中心资料编辑弹窗
 */
public class HRMyHomePopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private Dialog dialog;
    private View inflate;
    private TextView tv_edit, tv_setting;

    public HRMyHomePopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_hr_my_home, null);
        tv_edit = view.findViewById(R.id.tv_edit);
        tv_setting = view.findViewById(R.id.tv_setting);
        tv_edit.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.25));
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 1.0f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, -150, 0);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_edit) {//编辑资料
            context.startActivity(new Intent(context, HRMyEditDataActivity.class));
            dismiss();//取消弹窗
        } else if (i == R.id.tv_setting) {//推荐设置
            context.startActivity(new Intent(context, HRSignJurisdictionActivity.class));
            dismiss();//取消弹窗
        }
    }

}