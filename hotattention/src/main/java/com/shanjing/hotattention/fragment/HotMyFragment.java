package com.shanjing.hotattention.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.activity.EditDataActivity;
import com.shanjing.hotattention.activity.IReleaseActivity;
import com.shanjing.hotattention.activity.MyCollectActivity;
import com.shanjing.hotattention.activity.MyFeedbackActivity;
import com.shanjing.hotattention.activity.MyInterestActivity;
import com.shanjing.hotattention.activity.SettingActivity;

/**
 * 热点关注我的
 */
public class HotMyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_edit;
    private LinearLayout ll_i_release, ll_my_interest, ll_my_collect, ll_my_like, ll_my_comment;
    private RelativeLayout rl_my_feedback, rl_setting;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_hot_my;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        tv_edit = view.findViewById(R.id.tv_edit);
        ll_i_release = view.findViewById(R.id.ll_i_release);
        ll_my_interest = view.findViewById(R.id.ll_my_interest);
        ll_my_collect = view.findViewById(R.id.ll_my_collect);
        ll_my_like = view.findViewById(R.id.ll_my_like);
        ll_my_comment = view.findViewById(R.id.ll_my_comment);
        rl_my_feedback = view.findViewById(R.id.rl_my_feedback);
        rl_setting = view.findViewById(R.id.rl_setting);
        tv_edit.setOnClickListener(this);
        ll_i_release.setOnClickListener(this);
        ll_my_interest.setOnClickListener(this);
        ll_my_collect.setOnClickListener(this);
        ll_my_like.setOnClickListener(this);
        ll_my_comment.setOnClickListener(this);
        rl_my_feedback.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
    }

    @Override
    public void fetchData() {
        Log.d("ssss", "444---------*********/////////");

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_edit) {//点击编辑资料
            startActivity(new Intent(getActivity(), EditDataActivity.class));
        } else if (i == R.id.ll_i_release) {//我的发布
            startActivity(new Intent(getActivity(), IReleaseActivity.class));
        } else if (i == R.id.ll_my_interest) {//我的关注
            startActivity(new Intent(getActivity(), MyInterestActivity.class));
        } else if (i == R.id.ll_my_collect) {//我的收藏
            startActivity(new Intent(getActivity(), MyCollectActivity.class));
        } else if (i == R.id.ll_my_like) {//我的点赞
            Intent intent = new Intent(getActivity(), MyCollectActivity.class);
            intent.putExtra("flag2", 2);
            startActivity(intent);
        } else if (i == R.id.ll_my_comment) {//我的评论
            Intent intent = new Intent(getActivity(), MyCollectActivity.class);
            intent.putExtra("flag3", 3);
            startActivity(intent);
        } else if (i == R.id.rl_my_feedback) {//我的反馈
            startActivity(new Intent(getActivity(), MyFeedbackActivity.class));
        } else if (i == R.id.rl_setting) {//我的设置
            startActivity(new Intent(getActivity(), SettingActivity.class));
        }
    }
}
