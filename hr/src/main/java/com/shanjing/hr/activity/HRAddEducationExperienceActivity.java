package com.shanjing.hr.activity;

import android.app.Dialog;
import android.content.Intent;
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

import com.base.commonlib.BaseActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.HRMajorEvent;
import com.shanjing.hr.bean.HRSchoolNameEvent;
import com.shanjing.hr.view.PickValueView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 新增教育经历
 */
public class HRAddEducationExperienceActivity extends BaseActivity implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_school_name, tv_education_background, tv_major, tv_time_quantum;
    private ImageView iv;
    private RelativeLayout rl_school, rl_education_background, rl_major, rl_time_quantum,
            rl_school_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_hradd_education_experience);
        initView();
        initData();
    }

    /**
     * 完整的月份数据1~12
     */
    private List<String> monthList = new ArrayList<>();
    /**
     * 滚轮选择器中年份的选项数据
     */
    private List<String> optionYears = new ArrayList<>();
    /**
     * 滚轮选择器中月份的选项数据
     */
    private List<List<String>> optionMonths = new ArrayList<>();

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        //月份获取到的数据是0~11，所以要加1
        int curMonth = calendar.get(Calendar.MONTH) + 1;

        //设置完整的月份数据，即1~12
        for (int i = curYear; i >= 1990; i--) {
            monthList.add(String.valueOf(i));
        }

        for (int i = curYear; i >= 1989; i--) {
            //对应年份的月份数据集合
            List<String> tempMonths = new ArrayList<>();
            if (i == 1989) {
                //设置最早时间“1900以前”
                optionYears.add("1990以前");
                tempMonths.add("1990以前");
                optionMonths.add(tempMonths);
            } else {
                //设置常规时间
                optionYears.add(String.valueOf(i));
                optionMonths.add(monthList);
            }
        }
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_school = findViewById(R.id.rl_school);
        tv_school_name = findViewById(R.id.tv_school_name);
        rl_education_background = findViewById(R.id.rl_education_background);
        tv_education_background = findViewById(R.id.tv_education_background);
        rl_major = findViewById(R.id.rl_major);
        tv_major = findViewById(R.id.tv_major);
        rl_time_quantum = findViewById(R.id.rl_time_quantum);
        tv_time_quantum = findViewById(R.id.tv_time_quantum);
        rl_school_experience = findViewById(R.id.rl_school_experience);
        ll_hr_back.setOnClickListener(this);
        rl_school.setOnClickListener(this);
        rl_education_background.setOnClickListener(this);
        rl_major.setOnClickListener(this);
        rl_time_quantum.setOnClickListener(this);
        rl_school_experience.setOnClickListener(this);
        tv_title.setText("新增教育经历");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    @Subscribe
    public void onEventMainThread(HRSchoolNameEvent hrSchoolNameEvent) {//获取学校名称并显示
        tv_school_name.setTextColor(getResources().getColor(R.color.color_text));
        tv_school_name.setText(hrSchoolNameEvent.getMessage());
    }

    @Subscribe
    public void onEventMainThread(HRMajorEvent hrMajorEvent) {//获取专业
        tv_major.setTextColor(getResources().getColor(R.color.color_text));
        tv_major.setText(hrMajorEvent.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private Dialog dialog;
    private View inflate;
    private OptionsPickerView multipleOp;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_school) {//学校
            startActivity(new Intent(this, HRSchoolActivity.class));
        } else if (i == R.id.rl_education_background) {//学历
            selectedStr = "专科";//重置工作性质
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
                    tv_education_background.setText(selectedStr);
                    tv_education_background.setTextColor(getResources().getColor(R.color.color_text));
                }
            });
            tv_title.setText("学历要求");
            String[] valueStr = new String[]{"高中", "专科", "本科", "研究生", "硕士", "博士"};
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
        } else if (i == R.id.rl_major) {//专业
            startActivity(new Intent(this, HRMajorActivity.class));
        } else if (i == R.id.rl_time_quantum) {//时间段
            multipleOp = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    tv_time_quantum.setText(new StringBuffer(optionYears.get(options1)).append("—").append(monthList.get(options2)));
                    tv_time_quantum.setTextColor(getResources().getColor(R.color.color_text));
                }
            }).setTitleText("时间段").build();
            multipleOp.setPicker(optionYears, optionMonths);
            multipleOp.show();
        } else if (i == R.id.rl_school_experience) {//在校经历
            startActivity(new Intent(this, HRSchoolExperienceActivity.class));
        }
    }

    private String selectedStr;

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        selectedStr = (String) leftValue;
    }
}
