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
import com.shanjing.hr.adapter.PlatformAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * 平台
 */
public class PlatformFragment extends Fragment {

    private RecyclerView rv_platform;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_platform, container, false);
        rv_platform = view.findViewById(R.id.rv_platform);
        initData();
        return view;
    }

    private List<String> list = Arrays.asList("51JOB", "boss直聘", "智联招聘");

    private void initData() {
        //设置管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rv_platform.setLayoutManager(linearLayoutManager);
        //设置适配器
        PlatformAdapter platformAdapter = new PlatformAdapter(getActivity(), list);
        rv_platform.setAdapter(platformAdapter);
    }

}
