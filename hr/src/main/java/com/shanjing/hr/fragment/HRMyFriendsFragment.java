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
import com.shanjing.hr.adapter.HRMyFriendsAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * 我的好友
 */
public class HRMyFriendsFragment extends Fragment {

    private RecyclerView rl_my_friends;

    public HRMyFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrmy_friends, container, false);
        rl_my_friends = view.findViewById(R.id.rl_my_friends);
        initView();
        return view;
    }

    private List<String> list = Arrays.asList("张晓山", "马杰杰", "李大璐", "李杰杰", "罗金凤",
            "Lucy Green", "张珲春", "金喜恶", "潘胜军", "刘强西", "张泽天");

    private void initView() {
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        rl_my_friends.setLayoutManager(linearLayoutManager);
        //设置适配器
        HRMyFriendsAdapter hrMyFriendsAdapter = new HRMyFriendsAdapter(getActivity(), list);
        rl_my_friends.setAdapter(hrMyFriendsAdapter);
    }

}
