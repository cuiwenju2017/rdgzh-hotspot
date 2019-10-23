package com.shanjing.hr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRCollectJobActivity;
import com.shanjing.hr.activity.HRCompanyAuthenticationActivity;
import com.shanjing.hr.activity.HRCompanyInterviewActivity;
import com.shanjing.hr.activity.HRJobManageActivity;
import com.shanjing.hr.activity.HRJoinUsSettingActivity;
import com.shanjing.hr.activity.HRReceivedResumeActivity;
import com.shanjing.hr.activity.HRRecruitmentAssistantActivity;

/**
 * 个人中心招聘
 */
public class HRMyGiveJobragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rl_company_interview, rl_received_resume, rl_job_manage,
            rl_company_authentication, rl_collect_job, rl_recruitment_assistant, rl_join_us_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrmy_give_jobragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rl_company_interview = view.findViewById(R.id.rl_company_interview);
        rl_received_resume = view.findViewById(R.id.rl_received_resume);
        rl_job_manage = view.findViewById(R.id.rl_job_manage);
        rl_company_authentication = view.findViewById(R.id.rl_company_authentication);
        rl_collect_job = view.findViewById(R.id.rl_collect_job);
        rl_recruitment_assistant = view.findViewById(R.id.rl_recruitment_assistant);
        rl_join_us_setting = view.findViewById(R.id.rl_join_us_setting);
        rl_company_interview.setOnClickListener(this);
        rl_received_resume.setOnClickListener(this);
        rl_job_manage.setOnClickListener(this);
        rl_company_authentication.setOnClickListener(this);
        rl_collect_job.setOnClickListener(this);
        rl_recruitment_assistant.setOnClickListener(this);
        rl_join_us_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_company_interview) {//公司面试
            startActivity(new Intent(getActivity(), HRCompanyInterviewActivity.class));
        } else if (i == R.id.rl_received_resume) {//已收简历
            startActivity(new Intent(getActivity(), HRReceivedResumeActivity.class));
        } else if (i == R.id.rl_job_manage) {//职位管理
            startActivity(new Intent(getActivity(), HRJobManageActivity.class));
        } else if (i == R.id.rl_company_authentication) {//公司认证
            startActivity(new Intent(getActivity(), HRCompanyAuthenticationActivity.class));
        } else if (i == R.id.rl_collect_job) {//收藏职位
            startActivity(new Intent(getActivity(), HRCollectJobActivity.class));
        } else if (i == R.id.rl_recruitment_assistant) {//招聘助手
            startActivity(new Intent(getActivity(), HRRecruitmentAssistantActivity.class));
        } else if (i == R.id.rl_join_us_setting) {//招聘设置
            startActivity(new Intent(getActivity(), HRJoinUsSettingActivity.class));
        }
    }
}
