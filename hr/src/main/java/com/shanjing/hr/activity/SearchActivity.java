package com.shanjing.hr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.FirstEvent;
import com.shanjing.hr.view.ShowButtonLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 搜索
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_cancel, tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //注册EventBus
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_search);
        initView();
        initData();
    }

    private ShowButtonLayout mShowBtnLayout, sbl_hot_search, sbl_hot_company;
    private String[] hotWords = {"行政主管", "人事经理", "财务主管", "java", "水电工程师", "工业设计师"};
    private String[] hotSearch = {"人事专员", "行政专员", "职业讲师", "iOS", "电焊工程师", "室内设计师", "平面设计师"};
    private String[] hotCompany = {"杭州闪镜网络科技有限公司", "浙江绿源贸易有限公司", "土巴兔", "苏宁易购", "蓝天科技"};

    private void initData() {
        //历史搜索
        for (int i = 0; i < hotWords.length; i++) {
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.hot_search_tv, mShowBtnLayout, false);
            view.setText(hotWords[i]);
            view.setTag(hotWords[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String keyword = (String) view.getTag();
                    Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();
                }
            });
            mShowBtnLayout.addView(view);
        }
        //热门搜索
        for (int i = 0; i < hotSearch.length; i++) {
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.hot_search_tv, sbl_hot_search, false);
            view.setText(hotSearch[i]);
            view.setTag(hotSearch[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String keyword = (String) view.getTag();
                    Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();
                }
            });
            sbl_hot_search.addView(view);
        }
        //热门公司
        for (int i = 0; i < hotCompany.length; i++) {
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.hot_search_tv, sbl_hot_company, false);
            view.setText(hotCompany[i]);
            view.setTag(hotCompany[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String keyword = (String) view.getTag();
                    Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();
                }
            });
            sbl_hot_company.addView(view);
        }
    }

    private void initView() {
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_city = findViewById(R.id.tv_city);
        mShowBtnLayout = findViewById(R.id.mShowBtnLayout);
        sbl_hot_search = findViewById(R.id.sbl_hot_search);
        sbl_hot_company = findViewById(R.id.sbl_hot_company);
        tv_cancel.setOnClickListener(this);
        tv_city.setOnClickListener(this);
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        //显示城市
        tv_city.setText(event.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {//取消
            finish();
        } else if (i == R.id.tv_city) {//跳转到选择城市页面
            startActivity(new Intent(this, ChooseCityActivity.class));
        }
    }
}
