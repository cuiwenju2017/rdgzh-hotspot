package com.shanjing.hr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRAlreadyHaveAResumeActivity;
import com.shanjing.hr.activity.HRAttachmentResumeActivity;
import com.shanjing.hr.activity.HRAttentionMyCompanyActivity;
import com.shanjing.hr.activity.HRCollectActivity;
import com.shanjing.hr.activity.HREditResumeActivity;
import com.shanjing.hr.activity.HRJobAssistantActivity;
import com.shanjing.hr.activity.HRMYSettingActivity;
import com.shanjing.hr.activity.HRMyInterviewActivity;

/**
 * 个人中心求职
 */
public class HRMyWantedJobFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rl_my_interview, rl_edit_resume, rl_already_have_a_resume,
            rl_attachment_resume, rl_collect, rl_job_assistant, rl_attention_my_company, rl_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrmy_wanted_job, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rl_my_interview = view.findViewById(R.id.rl_my_interview);
        rl_edit_resume = view.findViewById(R.id.rl_edit_resume);
        rl_already_have_a_resume = view.findViewById(R.id.rl_already_have_a_resume);
        rl_attachment_resume = view.findViewById(R.id.rl_attachment_resume);
        rl_collect = view.findViewById(R.id.rl_collect);
        rl_job_assistant = view.findViewById(R.id.rl_job_assistant);
        rl_attention_my_company = view.findViewById(R.id.rl_attention_my_company);
        rl_setting = view.findViewById(R.id.rl_setting);
        rl_my_interview.setOnClickListener(this);
        rl_edit_resume.setOnClickListener(this);
        rl_already_have_a_resume.setOnClickListener(this);
        rl_attachment_resume.setOnClickListener(this);
        rl_collect.setOnClickListener(this);
        rl_job_assistant.setOnClickListener(this);
        rl_attention_my_company.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_my_interview) {//我的面试
            startActivity(new Intent(getActivity(), HRMyInterviewActivity.class));
        } else if (i == R.id.rl_edit_resume) {//编辑简历
            startActivity(new Intent(getActivity(), HREditResumeActivity.class));
        } else if (i == R.id.rl_already_have_a_resume) {//已投简历
            startActivity(new Intent(getActivity(), HRAlreadyHaveAResumeActivity.class));
        } else if (i == R.id.rl_attachment_resume) {//附件简历
            startActivity(new Intent(getActivity(), HRAttachmentResumeActivity.class));
        } else if (i == R.id.rl_collect) {//收藏
            startActivity(new Intent(getActivity(), HRCollectActivity.class));
        } else if (i == R.id.rl_job_assistant) {//求职助手
            startActivity(new Intent(getActivity(), HRJobAssistantActivity.class));
        } else if (i == R.id.rl_attention_my_company) {//关注我的公司
            startActivity(new Intent(getActivity(), HRAttentionMyCompanyActivity.class));
        } else if (i == R.id.rl_setting) {//设置
            startActivity(new Intent(getActivity(), HRMYSettingActivity.class));
        }
    }
}
