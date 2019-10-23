package com.shanjing.hr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanjing.hr.R;
import com.shanjing.hr.activity.JobWantedParticularsActivity;
import com.shanjing.hr.adapter.JobWantedAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 人力资源购物车
 */
public class HRCartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrcart, container, false);
        return view;
    }
}
