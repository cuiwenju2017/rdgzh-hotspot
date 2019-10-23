package com.shanjing.hr.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanjing.hr.R;
import com.shanjing.hr.view.PickValueView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 编辑资料
 */
public class HRMyEditDataActivity extends BaseActivity implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private LinearLayout ll_hr_back, ll_sex, ll_birthday;
    private TextView tv_title, tv_finished, tv_sex, tv_birthday;
    private EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrmy_edit_data);
        initView();
        initBrithdayPicker();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        tv_finished = findViewById(R.id.tv_finished);
        et_username = findViewById(R.id.et_username);
        ll_sex = findViewById(R.id.ll_sex);
        tv_sex = findViewById(R.id.tv_sex);
        ll_birthday = findViewById(R.id.ll_birthday);
        tv_birthday = findViewById(R.id.tv_birthday);
        ll_hr_back.setOnClickListener(this);
        ll_sex.setOnClickListener(this);
        ll_birthday.setOnClickListener(this);
        tv_title.setText("编辑资料");
        tv_finished.setText("保存");
        et_username.setCursorVisible(false);//隐藏光标
        et_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_username.setCursorVisible(true);//显示光标
            }
        });
    }

    private Dialog dialog;
    private View inflate;
    private TimePickerView brithdayCustomLunar;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_sex) {//性别选择
            selectedStr = "女";//重置工作性质
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.hr_dialog_experience, null);
            //获取控件
            RelativeLayout rl_cancle = inflate.findViewById(R.id.rl_cancle);
            PickValueView pickString = inflate.findViewById(R.id.pickString);
            RelativeLayout rl_finished = inflate.findViewById(R.id.rl_finished);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            //获取监听
            rl_cancle.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            rl_cancle.setOnClickListener(new View.OnClickListener() {//取消
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            rl_finished.setOnClickListener(new View.OnClickListener() {//完成
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    tv_sex.setText(selectedStr);
                    tv_sex.setTextColor(getResources().getColor(R.color.color_text));
                }
            });
            tv_title.setText("性别");
            String[] valueStr = new String[]{"男", "女"};
            pickString.setValueData(valueStr, valueStr[1]);

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
        } else if (i == R.id.ll_birthday) {//生日
            brithdayCustomLunar.show();
        }
    }

    private void initBrithdayPicker() {//出生年月
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1951, 0, 01);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR) - 15, 11, 01);
        //时间选择器 ，自定义布局
        brithdayCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_birthday.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.hr_dialog_brithday, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        RelativeLayout tvSubmit = v.findViewById(R.id.rl_finished);
                        RelativeLayout ivCancel = v.findViewById(R.id.rl_cancle);
                        TextView tv_title = v.findViewById(R.id.tv_title);
                        tv_title.setText("出生年月");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                brithdayCustomLunar.returnData();
                                brithdayCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                brithdayCustomLunar.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.argb(102, 102, 102, 1))//线条颜色
                //.setRangDate(startDate, selectedDate)//显示到当前时间
                .setContentTextSize(18)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.color_text))//滚轴字体颜色
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String selectedStr;

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        selectedStr = (String) leftValue;
    }

}
