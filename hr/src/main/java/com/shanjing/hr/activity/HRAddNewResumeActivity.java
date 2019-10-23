package com.shanjing.hr.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.HRContactWayEvent;
import com.shanjing.hr.bean.HRNameEvent;
import com.shanjing.hr.photoPicker.ImageLoader;
import com.shanjing.hr.photoPicker.ImgSelActivity;
import com.shanjing.hr.photoPicker.ImgSelConfig;
import com.shanjing.hr.utils.ImageLoaderUtils;
import com.shanjing.hr.view.PickValueView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 添加新简历
 */
public class HRAddNewResumeActivity extends BaseActivity implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_tip, tv_name, tv_job_time, tv_brithday, tv_contact_way, tv_sex;
    private ImageView iv;
    private RelativeLayout rl_head, rl_name, rl_join_time, rl_birthday, rl_contact_way, rl_sex,
            rl_occupational_history, rl_education_experience, rl_project_experience;
    private CircleImageView civ_head;
    private TimePickerView pvCustomLunar, brithdayCustomLunar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_hradd_new_resume);
        initLunarPicker();
        initBrithdayPicker();
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_head = findViewById(R.id.rl_head);
        civ_head = findViewById(R.id.civ_head);
        tv_tip = findViewById(R.id.tv_tip);
        rl_name = findViewById(R.id.rl_name);
        tv_name = findViewById(R.id.tv_name);
        rl_join_time = findViewById(R.id.rl_join_time);
        tv_job_time = findViewById(R.id.tv_job_time);
        rl_birthday = findViewById(R.id.rl_birthday);
        tv_brithday = findViewById(R.id.tv_brithday);
        rl_contact_way = findViewById(R.id.rl_contact_way);
        tv_contact_way = findViewById(R.id.tv_contact_way);
        rl_sex = findViewById(R.id.rl_sex);
        tv_sex = findViewById(R.id.tv_sex);
        rl_occupational_history = findViewById(R.id.rl_occupational_history);
        rl_education_experience = findViewById(R.id.rl_education_experience);
        rl_project_experience = findViewById(R.id.rl_project_experience);
        ll_hr_back.setOnClickListener(this);
        rl_head.setOnClickListener(this);
        rl_name.setOnClickListener(this);
        rl_join_time.setOnClickListener(this);
        rl_birthday.setOnClickListener(this);
        rl_contact_way.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_occupational_history.setOnClickListener(this);
        rl_education_experience.setOnClickListener(this);
        rl_project_experience.setOnClickListener(this);
        tv_title.setText("添加新简历");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    @Subscribe
    public void onEventMainThread(HRNameEvent hrNameEvent) {//获取姓名
        tv_name.setTextColor(getResources().getColor(R.color.color_text));
        tv_name.setText(hrNameEvent.getMessage());
    }

    @Subscribe
    public void onEventMainThread(HRContactWayEvent hrContactWayEvent) {//获取姓名
        tv_contact_way.setTextColor(getResources().getColor(R.color.color_text));
        tv_contact_way.setText(hrContactWayEvent.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.rl_head) {//头像选择
            //去寻找是否已经有了相机的权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                ImgSelConfig config = new ImgSelConfig.Builder(loader)
                        // 是否多选
                        .multiSelect(false)
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
                        //按钮字体颜色
                        .btnTextColor(ContextCompat.getColor(this, R.color.colorBule))
                        .build();
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
            } else {
                //否则去请求相机权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
            }
        } else if (i == R.id.rl_name) {//姓名
            startActivity(new Intent(this, HRNameActivity.class));
        } else if (i == R.id.rl_join_time) {//参加工作时间
            pvCustomLunar.show();
        } else if (i == R.id.rl_birthday) {//出生年月
            brithdayCustomLunar.show();
        } else if (i == R.id.rl_contact_way) {//联系方式
            startActivity(new Intent(this, HRContactWayActivity.class));
        } else if (i == R.id.rl_sex) {//性别选择
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
        } else if (i == R.id.rl_occupational_history) {//工作经历
            startActivity(new Intent(this, HROccupationalHistoryActivity.class));
        } else if (i == R.id.rl_education_experience) {//教育经历
            startActivity(new Intent(this, HREducationExperienceActivity.class));
        } else if (i == R.id.rl_project_experience) {//项目经验
            startActivity(new Intent(this, HRProjectExperienceActivity.class));
        }
    }

    private void initLunarPicker() {//工作时间
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1990, 0, 01);
       /* Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);*/
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_job_time.setText(getTime(date));
                tv_job_time.setTextColor(getResources().getColor(R.color.color_text));
            }
        })
                .setDate(selectedDate)
                .setLayoutRes(R.layout.hr_dialog_job_time, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        RelativeLayout tvSubmit = v.findViewById(R.id.rl_finished);
                        RelativeLayout ivCancel = v.findViewById(R.id.rl_cancle);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
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
                tv_brithday.setText(getTime(date));
                tv_brithday.setTextColor(getResources().getColor(R.color.color_text));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.hr_dialog_job_time, new CustomListener() {

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
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.argb(102, 102, 102, 1))//线条颜色
                //.setRangDate(startDate, selectedDate)//显示到当前时间
                .setContentTextSize(18)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.color_text))//滚轴字体颜色
                .build();
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.display(context, imageView, path);
        }
    };

    private int REQUEST_CODE = 120;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            tv_tip.setVisibility(View.GONE);//隐藏提示
            civ_head.setVisibility(View.VISIBLE);//显示头像
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            Glide.with(this).load(pathList.get(0)).into(civ_head);//显示图片
        }
    }

    private String selectedStr;

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        selectedStr = (String) leftValue;
    }
}
