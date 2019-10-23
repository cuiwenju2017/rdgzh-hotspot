package com.shanjing.hr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.HRProjectNameEvent;
import com.shanjing.hr.bean.HRScaleEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 添加项目经验
 */
public class HRAddProjectExperienceActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_project_name, tv_start_time, tv_end_time;
    private ImageView iv;
    private RelativeLayout rl_project_name, rl_start_time, rl_end_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_hradd_project_experience);
        initView();
        initStartTime();
        initEndTime();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_project_name = findViewById(R.id.rl_project_name);
        tv_project_name = findViewById(R.id.tv_project_name);
        rl_start_time = findViewById(R.id.rl_start_time);
        rl_end_time = findViewById(R.id.rl_end_time);
        tv_start_time = findViewById(R.id.tv_start_time);
        tv_end_time = findViewById(R.id.tv_end_time);
        ll_hr_back.setOnClickListener(this);
        rl_project_name.setOnClickListener(this);
        rl_start_time.setOnClickListener(this);
        rl_end_time.setOnClickListener(this);
        tv_title.setText("新增项目经验");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    @Subscribe
    public void onEventMainThread(HRProjectNameEvent hrProjectNameEvent) {//获取公司规模并显示
        tv_project_name.setTextColor(getResources().getColor(R.color.color_text));
        tv_project_name.setText(hrProjectNameEvent.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private TimePickerView pvStartTime, pvEndTime;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_project_name) {//项目名称
            startActivity(new Intent(this, HRProjectNameActivity.class));
        } else if (i == R.id.rl_start_time) {//项目开始时间
            pvStartTime.show();
        } else if (i == R.id.rl_end_time) {//项目结束时间
            pvEndTime.show();
        }
    }

    private void initStartTime() {//开始时间
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1990, 0, 01);
       /* Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);*/
        //时间选择器 ，自定义布局
        pvStartTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_start_time.setText(getTime(date));
                tv_start_time.setTextColor(getResources().getColor(R.color.color_text));
            }
        })
                .setDate(selectedDate)
                .setLayoutRes(R.layout.hr_dialog_job_time, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        RelativeLayout tvSubmit = v.findViewById(R.id.rl_finished);
                        RelativeLayout ivCancel = v.findViewById(R.id.rl_cancle);
                        TextView tv_title = v.findViewById(R.id.tv_title);
                        tv_title.setText("开始时间");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvStartTime.returnData();
                                pvStartTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvStartTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.argb(102, 102, 102, 1))//线条颜色
                .setRangDate(startDate, selectedDate)//显示到当前时间
                .setContentTextSize(18)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.color_text))//滚轴字体颜色
                .build();
    }

    private void initEndTime() {//结束时间
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1990, 0, 01);
       /* Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);*/
        //时间选择器 ，自定义布局
        pvEndTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_end_time.setText(getTime(date));
                tv_end_time.setTextColor(getResources().getColor(R.color.color_text));
            }
        })
                .setDate(selectedDate)
                .setLayoutRes(R.layout.hr_dialog_job_time, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        RelativeLayout tvSubmit = v.findViewById(R.id.rl_finished);
                        RelativeLayout ivCancel = v.findViewById(R.id.rl_cancle);
                        TextView tv_title = v.findViewById(R.id.tv_title);
                        tv_title.setText("结束时间");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvEndTime.returnData();
                                pvEndTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvEndTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.argb(102, 102, 102, 1))//线条颜色
                .setRangDate(startDate, selectedDate)//显示到当前时间
                .setContentTextSize(18)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.color_text))//滚轴字体颜色
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        return format.format(date);
    }

}
