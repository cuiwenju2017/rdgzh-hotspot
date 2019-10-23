package com.shanjing.hotattention.utils;

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

import com.base.commonlib.utils.ToastUtil;
import com.shanjing.hotattention.R;

/**
 * 加关注、加好友弹窗
 */
public class AddFriendPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private Dialog dialog;
    private View inflate;
    private TextView tv_attention, tv_add_friend, tv_recommend_friend;

    public AddFriendPopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_dialog_add_friend, null);
        tv_attention = view.findViewById(R.id.tv_attention);
        tv_add_friend = view.findViewById(R.id.tv_add_friend);
        tv_recommend_friend = view.findViewById(R.id.tv_recommend_friend);
        tv_attention.setOnClickListener(this);
        tv_add_friend.setOnClickListener(this);
        tv_recommend_friend.setOnClickListener(this);
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
        showAsDropDown(view, -80, 10);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_attention) {//加关注
            tv_attention.setText("取消关注");
        } else if (i == R.id.tv_add_friend) {//加好友
            tv_add_friend.setText("删除好友");
        } else if (i == R.id.tv_recommend_friend) {//推荐给好友
            ToastUtil.showShort(context, "推荐给好友");
        }
    }

}