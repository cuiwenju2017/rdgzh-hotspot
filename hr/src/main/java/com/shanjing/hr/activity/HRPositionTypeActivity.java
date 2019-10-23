package com.shanjing.hr.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

/**
 * 职位类型
 */
public class HRPositionTypeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back;
    private TextView tv_title;
    private ImageView iv;
    private EditText et_job_name;
    private TextView tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrposition_type);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        et_job_name = findViewById(R.id.et_job_name);
        tv_num = findViewById(R.id.tv_num);
        ll_hr_back.setOnClickListener(this);
        tv_title.setText("选择职位类型");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        tv_num.setText(et_job_name.getText().toString().length() + "/20");

        //职位名称输入的监听
        et_job_name.addTextChangedListener(new TextWatcher() {

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
                editStart = et_job_name.getSelectionStart();
                editEnd = et_job_name.getSelectionEnd();
                tv_num.setText(temp.length() + "/20");//输入后字数显示
                if (temp.length() > 20) {//输入字数限制
                    s.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    et_job_name.setText(s);//显示限制内的内容
                    et_job_name.setSelection(tempSelection);//光标焦点设置在行末
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        }
    }
}
