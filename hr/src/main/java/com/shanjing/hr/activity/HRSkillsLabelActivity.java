package com.shanjing.hr.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.utils.ToastUtil;
import com.shanjing.hr.R;
import com.shanjing.hr.view.PickValueView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 技能标签
 */
public class HRSkillsLabelActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title, tv_add_tag;
    private ImageView iv;
    private TagFlowLayout flowlayout;
    private String[] mVals = new String[]
            {"硬件销售", "网销", "保健品销售", "店员销售", "贵金属销售", "课程销售",
                    "汽车销售", "地推销售", "面销", "海外销售", "保险销售",
                    "快销品销售", "器械销售", "家电销售", "广告销售"};
    private int maxSelected = 3;
    private Set<Integer> selectedList;
    private String[] mValsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrskills_label);
        initView();
        initFlowLayout();
        initFlowLayoutSelected();
    }

    /**
     * 初始化所有标签的FlowLayout
     */
    private void initFlowLayout() {
        TagAdapter tagAdapter = new TagAdapter(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                TextView view = (TextView) View.inflate(HRSkillsLabelActivity.this, R.layout.flowlayout_textview_selected, null);
                view.setText(mVals[position]);
                return view;
            }
        };
        //预先设置选中
        tagAdapter.setSelectedList(0, 1);
        flowlayout.setAdapter(tagAdapter);

        //设置最大选中数
        flowlayout.setMaxSelectCount(maxSelected);

        //为FlowLayout的标签设置监听事件
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (selectedList.size() >= maxSelected) {
                    Toast.makeText(HRSkillsLabelActivity.this, "已达最大选中数" + maxSelected, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HRSkillsLabelActivity.this, "" + mVals[position], Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //为FlowLayout的标签设置选中监听事件
        flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                initFlowLayoutSelected();
            }
        });
    }

    /**
     * 初始化选中标签的FlowLayout
     */
    private void initFlowLayoutSelected() {
        int i = 0;
        //获得所有选中的position集合,例如[1,2,3,4]
        selectedList = flowlayout.getSelectedList();
        mValsSelected = new String[selectedList.size()];
        Iterator<Integer> iterator = selectedList.iterator();
        while (iterator.hasNext()) {
            mValsSelected[i] = mVals[iterator.next()];
            i++;
        }
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        flowlayout = findViewById(R.id.flowlayout);
        tv_add_tag = findViewById(R.id.tv_add_tag);
        ll_hr_back.setOnClickListener(this);
        tv_add_tag.setOnClickListener(this);
        tv_title.setText("技能标签");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
    }

    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.tv_add_tag) {
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.dialog_hr_tag, null);
            //获取控件
            TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
            final EditText et_tag = inflate.findViewById(R.id.et_tag);
            //获取监听
            et_tag.addTextChangedListener(new TextWatcher() {
                private CharSequence temp;
                private int editStart;
                private int editEnd;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    temp = s;
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    editStart = et_tag.getSelectionStart();
                    editEnd = et_tag.getSelectionEnd();
                    if (temp.length() > 6) {//输入字数限制
                        s.delete(editStart - 1, editEnd);//删除限制外的内容
                        int tempSelection = editStart;
                        et_tag.setText(s);//显示限制内的内容
                        et_tag.setSelection(tempSelection);//光标焦点设置在行末
                    }
                }
            });
            tv_cancel.setOnClickListener(new View.OnClickListener() {//取消
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.CENTER);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = 700;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        }
    }

}
