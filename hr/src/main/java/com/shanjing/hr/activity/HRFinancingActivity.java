package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.HRFinancingAdapter;
import com.shanjing.hr.adapter.JobWantedAdapter;
import com.shanjing.hr.bean.HRCompanyPhoneEvent;
import com.shanjing.hr.bean.HRFinancingEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

/**
 * 融资状况
 */
public class HRFinancingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private ImageView iv;
    private RecyclerView rl_financing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrfinancing);
        initView();
    }

    private List<String> list = Arrays.asList("不需要融资", "种子轮", "天使轮", "A轮", "B轮", "C轮", "IPO");

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_financing = findViewById(R.id.rl_financing);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("融资状况");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        //设置适配器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rl_financing.setLayoutManager(linearLayoutManager);
        final HRFinancingAdapter hrFinancingAdapter = new HRFinancingAdapter(this, list);
        rl_financing.setAdapter(hrFinancingAdapter);
        hrFinancingAdapter.setOnItemClickListener(new HRFinancingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<String> holder, int pos) {
                //传到适配器  （适配器调用方法）
                hrFinancingAdapter.getIndex(pos);
                //刷新适配器
                hrFinancingAdapter.notifyDataSetChanged();
                finaicingText = holder.get(0);
            }
        });
    }

    private String finaicingText = "不需要融资";

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//保存选择的融资状况
            EventBus.getDefault().post(new HRFinancingEvent(finaicingText));
            finish();//关闭页面
        }
    }
}
