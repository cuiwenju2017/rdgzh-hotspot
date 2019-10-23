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

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.HRFinancingAdapter;
import com.shanjing.hr.adapter.HRScaleAdapter;
import com.shanjing.hr.bean.HRFinancingEvent;
import com.shanjing.hr.bean.HRScaleEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

/**
 * 公司规模
 */
public class HRScaleActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private ImageView iv;
    private RecyclerView rl_scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrscale);
        initView();
    }

    private List<String> list = Arrays.asList("少于10人", "10-99人", "100-299人", "300-更多");

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        rl_scale = findViewById(R.id.rl_scale);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("公司规模");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        //设置适配器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rl_scale.setLayoutManager(linearLayoutManager);
        final HRScaleAdapter hrScaleAdapter = new HRScaleAdapter(this, list);
        rl_scale.setAdapter(hrScaleAdapter);
        hrScaleAdapter.setOnItemClickListener(new HRScaleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<String> holder, int pos) {
                //传到适配器  （适配器调用方法）
                hrScaleAdapter.getIndex(pos);
                //刷新适配器
                hrScaleAdapter.notifyDataSetChanged();
                scaleText = holder.get(0);
            }
        });
    }

    private String scaleText = "少于10人";

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//保存公司规模
            EventBus.getDefault().post(new HRScaleEvent(scaleText));
            finish();//关闭页面
        }
    }
}
