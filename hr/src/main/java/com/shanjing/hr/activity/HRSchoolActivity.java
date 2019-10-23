package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.HRCompanyNameEvent;
import com.shanjing.hr.bean.HRSchoolNameEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 学校
 */
public class HRSchoolActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title, tv_num;
    private ImageView iv;
    private EditText et_school_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrschool);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        et_school_name = findViewById(R.id.et_school_name);
        tv_num = findViewById(R.id.tv_num);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("学校");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        tv_num.setText(Html.fromHtml("<font color='#FF0000'>" + et_school_name.getText().toString().length() + "</font>" + "/30"));
        et_school_name.addTextChangedListener(new TextWatcher() {
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
                editStart = et_school_name.getSelectionStart();
                editEnd = et_school_name.getSelectionEnd();
                tv_num.setText(Html.fromHtml("<font color='#FF0000'>" + temp.length() + "</font>" + "/30"));//输入后字数显示
                if (temp.length() > 30) {//输入字数限制
                    s.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    et_school_name.setText(s);//显示限制内的内容
                    et_school_name.setSelection(tempSelection);//光标焦点设置在行末
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//提交学校名称
            EventBus.getDefault().post(new HRSchoolNameEvent(et_school_name.getText().toString()));//保存学校名称
            finish();//关闭页面
        }
    }
}
