package com.shanjing.hr.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRAttachmentResumeActivity;
import com.shanjing.hr.activity.HRDesiredIndustryActivity;
import com.shanjing.hr.activity.HRExpectedPositionActivity;
import com.shanjing.hr.view.PickValueView;

/**
 * 发布求职
 */
public class HRSignJobWantedFragment extends Fragment implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private TextView tv_rule, tv_job_category, tv_salary_expectation;
    private RelativeLayout rl_sync, rl_choose_position, rl_desired_industry, rl_job_category,
            rl_salary_expectation, rl_attachment_resume;
    private ImageView iv_choose;

    public HRSignJobWantedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrsign_job_wanted, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_rule = view.findViewById(R.id.tv_rule);
        rl_sync = view.findViewById(R.id.rl_sync);
        iv_choose = view.findViewById(R.id.iv_choose);
        rl_choose_position = view.findViewById(R.id.rl_choose_position);
        rl_desired_industry = view.findViewById(R.id.rl_desired_industry);
        rl_job_category = view.findViewById(R.id.rl_job_category);
        tv_job_category = view.findViewById(R.id.tv_job_category);
        rl_salary_expectation = view.findViewById(R.id.rl_salary_expectation);
        tv_salary_expectation = view.findViewById(R.id.tv_salary_expectation);
        rl_attachment_resume = view.findViewById(R.id.rl_attachment_resume);
        rl_sync.setOnClickListener(this);
        rl_choose_position.setOnClickListener(this);
        rl_desired_industry.setOnClickListener(this);
        rl_job_category.setOnClickListener(this);
        tv_job_category.setOnClickListener(this);
        rl_salary_expectation.setOnClickListener(this);
        rl_attachment_resume.setOnClickListener(this);
        tv_rule.setText(Html.fromHtml("发布职位即表示同意遵守<font color='#FF0000'>《人力资源职位信息发布规则》</font>，如违反规定可能导致您的账号被锁定。"));
    }

    private boolean isChoose = false;
    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_sync) {//是否同步到动态
            if (!isChoose) {
                iv_choose.setBackgroundResource(R.drawable.check_hr_on_recommendation);
                isChoose = true;
            } else {
                iv_choose.setBackgroundResource(R.drawable.check_hr_off_recommendation);
                isChoose = false;
            }
        } else if (i == R.id.rl_choose_position) {//选择期望职位
            startActivity(new Intent(getActivity(), HRExpectedPositionActivity.class));
        } else if (i == R.id.rl_desired_industry) {//期望行业
            startActivity(new Intent(getActivity(), HRDesiredIndustryActivity.class));
        } else if (i == R.id.rl_job_category) {//工作性质
            selectedStr = "兼职";//重置工作性质
            dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.hr_dialog_experience, null);
            //获取控件
            RelativeLayout rl_cancle = inflate.findViewById(R.id.rl_cancle);
            PickValueView pickString = inflate.findViewById(R.id.pickString);
            RelativeLayout rl_finished = inflate.findViewById(R.id.rl_finished);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            //获取监听
            rl_cancle.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            rl_finished.setOnClickListener(new View.OnClickListener() {//完成
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    tv_job_category.setText(selectedStr);
                    tv_job_category.setTextColor(getResources().getColor(R.color.color_text));
                }
            });
            tv_title.setText("工作性质");
            String[] valueStr = new String[]{"全职", "兼职", "实习", "全部"};
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
        } else if (i == R.id.rl_salary_expectation) {//期望薪资
            selectedStr = "3k以下";//重置薪资范围
            dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.hr_dialog_experience, null);
            //获取控件
            RelativeLayout rl_cancle = inflate.findViewById(R.id.rl_cancle);
            PickValueView pickString = inflate.findViewById(R.id.pickString);
            RelativeLayout rl_finished = inflate.findViewById(R.id.rl_finished);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            //获取监听
            rl_cancle.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            rl_finished.setOnClickListener(new View.OnClickListener() {//完成
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    tv_salary_expectation.setText(selectedStr);
                    tv_salary_expectation.setTextColor(getResources().getColor(R.color.color_text));
                }
            });
            tv_title.setText("期望薪资");
            String[] valueStr = new String[]{"面议", "3k以下", "3-5k", "5-7k", "7-9k", "9-11k",
                    "11k以上"};
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
        } else if (i == R.id.rl_attachment_resume) {//附件简历
            startActivity(new Intent(getActivity(), HRAttachmentResumeActivity.class));
        }
    }

    private String selectedStr;

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        selectedStr = (String) leftValue;//拿到选择的经验要求
    }
}
