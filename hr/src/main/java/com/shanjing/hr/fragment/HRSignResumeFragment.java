package com.shanjing.hr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.HRAddNewResumeActivity;

/**
 * 发布简历
 */
public class HRSignResumeFragment extends Fragment implements View.OnClickListener {

    private TextView tv_rule, tv_add_new_resume;

    public HRSignResumeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrsign_resume, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_rule = view.findViewById(R.id.tv_rule);
        tv_add_new_resume = view.findViewById(R.id.tv_add_new_resume);
        tv_add_new_resume.setOnClickListener(this);
        tv_rule.setText(Html.fromHtml("发布职位即表示同意遵守<font color='#FF0000'>《人力资源职位信息发布规则》</font>，如违反规定可能导致您的账号被锁定。"));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_add_new_resume) {//添加新简历
            startActivity(new Intent(getActivity(), HRAddNewResumeActivity.class));
        }
    }
}
