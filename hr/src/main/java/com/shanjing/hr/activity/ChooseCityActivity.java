package com.shanjing.hr.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.google.gson.Gson;
import com.shanjing.hr.R;
import com.shanjing.hr.adapter.ChooseCity2Adapter;
import com.shanjing.hr.adapter.ChooseCity3Adapter;
import com.shanjing.hr.adapter.ChooseCityAdapter;
import com.shanjing.hr.bean.CityBean;
import com.shanjing.hr.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 选择城市
 */
public class ChooseCityActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;
    private RecyclerView rv_province, rv_city, rv_district;
    private LinearLayout ll_hr_back, ll_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        initView();
        initdata();
    }

    List<CityBean.DataBean> list;
    List<CityBean.DataBean.CityListBeanX> list2;
    List<CityBean.DataBean.CityListBeanX.CityListBean> list3;

    private void initdata() {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("china_city_data.json");
            String data = convertStreamToString(inputStream);
            Gson gson = new Gson();
            final CityBean supperclass = gson.fromJson(data, CityBean.class);
            list = supperclass.getData();
            list2 = supperclass.getData().get(0).getCityList();
            list3 = supperclass.getData().get(0).getCityList().get(0).getCityList();
            //设置管理器
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_province.setLayoutManager(linearLayoutManager);
            //设置省适配器
            final ChooseCityAdapter chooseCityAdapter = new ChooseCityAdapter(ChooseCityActivity.this, list);
            rv_province.setAdapter(chooseCityAdapter);

            //省item点击事件
            chooseCityAdapter.setOnItemClickListener(new ChooseCityAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(CityBean.DataBean holder, int pos) {
                    //传到适配器  （适配器调用方法）
                    chooseCityAdapter.getIndex(pos);
                    //刷新适配器
                    chooseCityAdapter.notifyDataSetChanged();
                    //传值到市列表并改变市列表
                    list2 = supperclass.getData().get(pos).getCityList();
                    StaggeredGridLayoutManager linearLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                    linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                    rv_city.setLayoutManager(linearLayoutManager2);
                    final ChooseCity2Adapter chooseCityAdapter2 = new ChooseCity2Adapter(ChooseCityActivity.this, list2);
                    rv_city.setAdapter(chooseCityAdapter2);

                    //市item点击事件
                    chooseCityAdapter2.setOnItemClickListener(new ChooseCity2Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(CityBean.DataBean.CityListBeanX holder, int pos) {
                            //传到适配器  （适配器调用方法）
                            chooseCityAdapter2.getIndex(pos);
                            //刷新适配器
                            chooseCityAdapter2.notifyDataSetChanged();

                            //EventBus保存城市名
                            EventBus.getDefault().post(new FirstEvent(holder.getName()));

                            //传值到区列表并改变区列表
                            list3 = list2.get(pos).getCityList();
                            StaggeredGridLayoutManager linearLayoutManager3 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
                            linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
                            rv_district.setLayoutManager(linearLayoutManager3);
                            final ChooseCity3Adapter chooseCity3Adapter = new ChooseCity3Adapter(ChooseCityActivity.this, list3);
                            rv_district.setAdapter(chooseCity3Adapter);

                            //区item点击事件
                            chooseCity3Adapter.setOnItemClickListener(new ChooseCity3Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(CityBean.DataBean.CityListBeanX.CityListBean holder, int pos) {
                                    //传到适配器  （适配器调用方法）
                                    chooseCity3Adapter.getIndex(pos);
                                    //刷新适配器
                                    chooseCity3Adapter.notifyDataSetChanged();
                                    Toast.makeText(ChooseCityActivity.this, "" + holder.getName(), Toast.LENGTH_SHORT).show();
                                    finish();//销毁页面
                                }
                            });
                        }
                    });
                }
            });

            //设置市适配器
            StaggeredGridLayoutManager linearLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_city.setLayoutManager(linearLayoutManager2);
            ChooseCity2Adapter chooseCity2Adapter = new ChooseCity2Adapter(ChooseCityActivity.this, list2);
            rv_city.setAdapter(chooseCity2Adapter);

            //设置区适配器
            StaggeredGridLayoutManager linearLayoutManager3 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            rv_district.setLayoutManager(linearLayoutManager3);
            ChooseCity3Adapter chooseCity3Adapter = new ChooseCity3Adapter(ChooseCityActivity.this, list3);
            rv_district.setAdapter(chooseCity3Adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        rv_province = findViewById(R.id.rv_province);
        rv_city = findViewById(R.id.rv_city);
        rv_district = findViewById(R.id.rv_district);
        ll_hr_back = findViewById(R.id.ll_hr_back);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("选择城市");
        ll_menu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
