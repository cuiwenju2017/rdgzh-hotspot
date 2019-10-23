package com.shanjing.hr.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.view.AddressPickerView;
import com.shanjing.hr.view.PickValueView;

/**
 * 新增工作地点
 */
public class HRAddressActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_finished, tv_show_address;
    private RelativeLayout rl_choose_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hraddress);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        tv_finished = findViewById(R.id.tv_finished);
        rl_choose_address = findViewById(R.id.rl_choose_address);
        tv_show_address = findViewById(R.id.tv_show_address);
        ll_hr_back.setOnClickListener(this);
        rl_choose_address.setOnClickListener(this);
        tv_title.setText("新增工作地点");
        tv_finished.setText("保存");
    }

    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_choose_address) {//选择地址
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.hr_dialog_choose_address, null);
            //获取控件
            ImageView tv_cancle = inflate.findViewById(R.id.tv_cancle);
            AddressPickerView addressView = inflate.findViewById(R.id.apvAddress);
            //获取监听
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
                @Override
                public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                    tv_show_address.setText(address);
                    dialog.dismiss();
                }
            });

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
        }
    }
}
