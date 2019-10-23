package com.shanjing.mymeishi.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.bumptech.glide.Glide;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.View.PickValueView;

/**
 * 购买清单
 */
public class BuyListActivity extends BaseActivity implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private TextView tv_title, tv_num, tv_zfb, tv_wx, tv_my_money, tv_cancel, tv_zffs, tv_cj_num, tv_name;
    private RelativeLayout rl_back, rl_zffs, rl_cj_num;
    private ImageView iv_minus, iv_add, iv_fm;
    private int num = 1;
    private Dialog dialog;
    private View inflate;
    PickValueView pickString;
    private String name, icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);
        Intent intent = getIntent();
        icon = intent.getStringExtra("icon");
        name = intent.getStringExtra("name");
        initView();
        initData();
        tv_num.setText("" + num);
    }

    private void initData() {
        tv_title.setText("购买清单");
        tv_name.setText(name);
        Glide.with(this).load(icon).into(iv_fm);
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        rl_back = findViewById(R.id.rl_back);
        tv_num = findViewById(R.id.tv_num);
        iv_minus = findViewById(R.id.iv_minus);
        iv_add = findViewById(R.id.iv_add);
        rl_zffs = findViewById(R.id.rl_zffs);
        tv_zffs = findViewById(R.id.tv_zffs);
        rl_cj_num = findViewById(R.id.rl_cj_num);
        tv_cj_num = findViewById(R.id.tv_cj_num);
        tv_name = findViewById(R.id.tv_name);
        iv_fm = findViewById(R.id.iv_fm);
        rl_back.setOnClickListener(this);
        iv_minus.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        rl_zffs.setOnClickListener(this);
        rl_cj_num.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_back) {
            finish();
        } else if (i == R.id.rl_cj_num) {//选择对话框
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_cj_num, null);
            //获取组件
            tv_cancel = contentView.findViewById(R.id.tv_cancel);
            pickString = contentView.findViewById(R.id.pickString);
            //获取Dialog的监听
            tv_cancel.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            String[] valueStr = new String[]{"无需餐具", "1人", "2人", "3人", "4人", "5人",
                    "6人", "7人", "8人"};
            pickString.setValueData(valueStr, valueStr[1]);
            dialog.setContentView(contentView);
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = getResources().getDisplayMetrics().widthPixels;
            contentView.setLayoutParams(layoutParams);
            dialog.getWindow().setGravity(Gravity.BOTTOM);//弹窗位置
            dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
            dialog.show();//显示弹窗
        } else if (i == R.id.rl_zffs) {
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.dialog_zffs, null);
            //获取控件
            tv_zfb = inflate.findViewById(R.id.tv_zfb);
            tv_wx = inflate.findViewById(R.id.tv_wx);
            tv_my_money = inflate.findViewById(R.id.tv_my_money);
            tv_cancel = inflate.findViewById(R.id.tv_cancel);
            //获取监听
            tv_cancel.setOnClickListener(this);
            tv_zfb.setOnClickListener(this);
            tv_wx.setOnClickListener(this);
            tv_my_money.setOnClickListener(this);
            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        } else if (i == R.id.tv_zfb) {
            tv_zffs.setText("支付宝");
            dialog.dismiss();
        } else if (i == R.id.tv_wx) {
            tv_zffs.setText("微信");
            dialog.dismiss();
        } else if (i == R.id.tv_my_money) {
            tv_zffs.setText("我的钱包");
            dialog.dismiss();
        } else if (i == R.id.tv_cancel) {
            dialog.dismiss();
        } else if (i == R.id.iv_minus) {
            if (num <= 1) {
                tv_num.setText("" + num);
            } else {
                tv_num.setText("" + num--);
            }
        } else if (i == R.id.iv_add) {
            tv_num.setText("" + num++);
        }
    }

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        String selectedStr = (String) leftValue;
        tv_cj_num.setText(selectedStr);
    }
}
