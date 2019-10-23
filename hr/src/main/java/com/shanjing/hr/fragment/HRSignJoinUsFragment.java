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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRCorporateInformationActivity;
import com.shanjing.hr.activity.HRJobAddressActivity;
import com.shanjing.hr.activity.HRJobDescriptionActivity;
import com.shanjing.hr.activity.HRJobVacancyActivity;
import com.shanjing.hr.activity.HRVideoAccessoryActivity;
import com.shanjing.hr.view.PickValueView;

/**
 * 发布招聘
 */
public class HRSignJoinUsFragment extends Fragment implements View.OnClickListener, PickValueView.onSelectedChangeListener {

    private TextView tv_rule, ex_experience, tv_pay, tv_job_category, tv_education;
    private RelativeLayout rl_sync, rl_choose_video, rl_corporate_information;
    private ImageView iv_choose;
    private RelativeLayout rl_job_vacancy, rl_experience, rl_pay, rl_job_category, rl_education,
            rl_job_description, rl_job_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrsign_join_us, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_rule = view.findViewById(R.id.tv_rule);
        rl_sync = view.findViewById(R.id.rl_sync);
        iv_choose = view.findViewById(R.id.iv_choose);
        rl_choose_video = view.findViewById(R.id.rl_choose_video);
        rl_job_vacancy = view.findViewById(R.id.rl_job_vacancy);
        rl_experience = view.findViewById(R.id.rl_experience);
        ex_experience = view.findViewById(R.id.ex_experience);
        rl_pay = view.findViewById(R.id.rl_pay);
        tv_pay = view.findViewById(R.id.tv_pay);
        rl_job_category = view.findViewById(R.id.rl_job_category);
        tv_job_category = view.findViewById(R.id.tv_job_category);
        rl_corporate_information = view.findViewById(R.id.rl_corporate_information);
        rl_education = view.findViewById(R.id.rl_education);
        tv_education = view.findViewById(R.id.tv_education);
        rl_job_description = view.findViewById(R.id.rl_job_description);
        rl_job_address = view.findViewById(R.id.rl_job_address);
        rl_sync.setOnClickListener(this);
        rl_choose_video.setOnClickListener(this);
        rl_job_vacancy.setOnClickListener(this);
        rl_experience.setOnClickListener(this);
        rl_pay.setOnClickListener(this);
        rl_job_category.setOnClickListener(this);
        rl_corporate_information.setOnClickListener(this);
        rl_education.setOnClickListener(this);
        rl_job_description.setOnClickListener(this);
        rl_job_address.setOnClickListener(this);
        tv_rule.setText(Html.fromHtml("发布职位即表示同意遵守<font color='#FF0000'>《人力资源职位信息发布规则》</font>，如违反规定可能导致您的账号被锁定。"));
    }

    private boolean isChoose = false;
    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_sync) {
            if (!isChoose) {
                iv_choose.setBackgroundResource(R.drawable.check_hr_on_recommendation);
                isChoose = true;
            } else {
                iv_choose.setBackgroundResource(R.drawable.check_hr_off_recommendation);
                isChoose = false;
            }
        } else if (i == R.id.rl_choose_video) {//选择视频
            startActivity(new Intent(getActivity(), HRVideoAccessoryActivity.class));//跳转到视频附件页面
        } else if (i == R.id.rl_job_vacancy) {//选择招聘职位
            startActivity(new Intent(getActivity(), HRJobVacancyActivity.class));//跳转到招聘职位页面
        } else if (i == R.id.rl_experience) {//经验要求选项
            selectedStr = "在校生";//重置选中第一个
            dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.hr_dialog_experience, null);
            //获取控件
            RelativeLayout rl_cancle = inflate.findViewById(R.id.rl_cancle);
            PickValueView pickString = inflate.findViewById(R.id.pickString);
            RelativeLayout rl_finished = inflate.findViewById(R.id.rl_finished);
            //获取监听
            rl_cancle.setOnClickListener(this);
            pickString.setOnSelectedChangeListener(this);
            rl_finished.setOnClickListener(new View.OnClickListener() {//完成
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    ex_experience.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "" + selectedStr, Toast.LENGTH_SHORT).show();
                }
            });

            String[] valueStr = new String[]{"无", "在校生", "应届生", "1-3年", "3-5年", "5-10年",
                    "10年以上"};
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
        } else if (i == R.id.rl_cancle) {//取消底部弹窗
            dialog.dismiss();
        } else if (i == R.id.rl_pay) {//薪资选择
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
                    tv_pay.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "" + selectedStr, Toast.LENGTH_SHORT).show();
                }
            });
            tv_title.setText("薪资范围");
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
        } else if (i == R.id.rl_job_category) {//选择工作性质
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
                    tv_job_category.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "" + selectedStr, Toast.LENGTH_SHORT).show();
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
        } else if (i == R.id.rl_corporate_information) {//公司基本信息
            startActivity(new Intent(getActivity(), HRCorporateInformationActivity.class));//跳转到公司基本信息页
        } else if (i == R.id.rl_education) {//学历选择
            selectedStr = "专科";//重置工作性质
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
                    tv_education.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "" + selectedStr, Toast.LENGTH_SHORT).show();
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
        } else if (i == R.id.rl_job_description) {//职位描述
            startActivity(new Intent(getActivity(), HRJobDescriptionActivity.class));
        } else if (i == R.id.rl_job_address) {//工作地点
            startActivity(new Intent(getActivity(), HRJobAddressActivity.class));
        }
    }

    private String selectedStr;

    @Override
    public void onSelected(PickValueView view, Object leftValue, Object middleValue, Object rightValue) {
        selectedStr = (String) leftValue;//拿到选择的经验要求
    }
}
