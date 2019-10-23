package com.shanjing.hr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanjing.hr.R;
import com.shanjing.hr.adapter.HRRecommendAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * 推荐
 */
public class HRRecommendFragment extends Fragment {

    private RecyclerView rv_recommend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrrecommend, container, false);
        rv_recommend = view.findViewById(R.id.rv_recommend);
        initData();
        return view;
    }

    private List<String> list = Arrays.asList("李蓝枫", "张雅", "白雪");

    private void initData() {
        //设置管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_recommend.setLayoutManager(linearLayoutManager);
        //设置适配器
        HRRecommendAdapter hrRecommendAdapter = new HRRecommendAdapter(getActivity(), list);
        rv_recommend.setAdapter(hrRecommendAdapter);
    }

}
