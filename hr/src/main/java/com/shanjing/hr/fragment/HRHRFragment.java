package com.shanjing.hr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.shanjing.hr.R;

/**
 * 个人中心人力资源
 */
public class HRHRFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb_thdings;
    public RadioGroup rg;
    private HRMyTidingsFragment hrMyTidingsFragment;
    private HRMyManagementFragment hrMyManagementFragment;
    private HRMyFriendFragment hrMyFriendFragment;
    private HRMyConcernFragment hrMyConcernFragment;
    private HRMyDataFragment hrMyDataFragment;
    private HRMyWalletFragment hrMyWalletFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrhr, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rb_thdings = view.findViewById(R.id.rb_thdings);
        rg = view.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rb_thdings.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb_thdings) {
            if (hrMyTidingsFragment == null) {
                hrMyTidingsFragment = new HRMyTidingsFragment();
                transaction.add(R.id.fl_my, hrMyTidingsFragment);
            } else {
                transaction.show(hrMyTidingsFragment);
            }
        } else if (checkedId == R.id.rb_management) {
            if (hrMyManagementFragment == null) {
                hrMyManagementFragment = new HRMyManagementFragment();
                transaction.add(R.id.fl_my, hrMyManagementFragment);
            } else {
                transaction.show(hrMyManagementFragment);
            }
        } else if (checkedId == R.id.rb_friend) {
            if (hrMyFriendFragment == null) {
                hrMyFriendFragment = new HRMyFriendFragment();
                transaction.add(R.id.fl_my, hrMyFriendFragment);
            } else {
                transaction.show(hrMyFriendFragment);
            }
        } else if (checkedId == R.id.rb_concern) {
            if (hrMyConcernFragment == null) {
                hrMyConcernFragment = new HRMyConcernFragment();
                transaction.add(R.id.fl_my, hrMyConcernFragment);
            } else {
                transaction.show(hrMyConcernFragment);
            }
        } else if (checkedId == R.id.rb_data) {
            if (hrMyDataFragment == null) {
                hrMyDataFragment = new HRMyDataFragment();
                transaction.add(R.id.fl_my, hrMyDataFragment);
            } else {
                transaction.show(hrMyDataFragment);
            }
        } else if (checkedId == R.id.rb_wallet) {
            if (hrMyWalletFragment == null) {
                hrMyWalletFragment = new HRMyWalletFragment();
                transaction.add(R.id.fl_my, hrMyWalletFragment);
            } else {
                transaction.show(hrMyWalletFragment);
            }
        }
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (hrMyTidingsFragment != null) {
            transaction.hide(hrMyTidingsFragment);
        }
        if (hrMyManagementFragment != null) {
            transaction.hide(hrMyManagementFragment);
        }
        if (hrMyFriendFragment != null) {
            transaction.hide(hrMyFriendFragment);
        }
        if (hrMyConcernFragment != null) {
            transaction.hide(hrMyConcernFragment);
        }
        if (hrMyDataFragment != null) {
            transaction.hide(hrMyDataFragment);
        }
        if (hrMyWalletFragment != null) {
            transaction.hide(hrMyWalletFragment);
        }
    }
}
