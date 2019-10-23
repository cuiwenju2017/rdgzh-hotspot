package com.shanjing.hr.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.shanjing.hr.R;

/**
 * 求职详情菜单弹窗
 */
public class HRMenuPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private Dialog dialog;
    private View inflate;
    private TextView tv_share, tv_collect;

    public HRMenuPopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_hr_menu, null);
        tv_share = view.findViewById(R.id.tv_share);
        tv_collect = view.findViewById(R.id.tv_collect);
        tv_share.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
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
        if (i == R.id.tv_share) {//分享
            Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.tv_collect) {//收藏
            Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
        }
    }

}