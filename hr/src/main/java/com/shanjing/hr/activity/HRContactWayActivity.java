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
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.bean.HRCompanyNameEvent;
import com.shanjing.hr.bean.HRContactWayEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 联系方式
 */
public class HRContactWayActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title;
    private ImageView iv;
    private EditText et_phone;
    private String contactWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrcontact_way);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        iv = findViewById(R.id.iv);
        et_phone = findViewById(R.id.et_phone);
        ll_menu = findViewById(R.id.ll_menu);
        ll_hr_back.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        tv_title.setText("联系方式");
        iv.setBackgroundResource(R.drawable.hr_icon_confirm);
        et_phone.addTextChangedListener(new TextWatcher() {

            int lastContentLength = 0;
            boolean isDelete = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringBuffer sb = new StringBuffer(s);
                //是否为输入状态
                isDelete = s.length() > lastContentLength ? false : true;
                //输入是第4，第9位，这时需要插入空格
                if (!isDelete && (s.length() == 4 || s.length() == 9)) {
                    if (s.length() == 4) {
                        sb.insert(3, " ");
                    } else {
                        sb.insert(8, " ");
                    }
                    setContent(sb);
                }
                //删除的位置到4，9时，剔除空格
                if (isDelete && (s.length() == 4 || s.length() == 9)) {
                    sb.deleteCharAt(sb.length() - 1);
                    setContent(sb);
                }
                lastContentLength = sb.length();
            }

            @Override
            public void afterTextChanged(Editable s) {
                contactWay = s.toString();
            }

            private void setContent(StringBuffer sb) {
                et_phone.setText(sb.toString());
                //移动光标到最后面
                et_phone.setSelection(sb.length());
            }

        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.ll_menu) {//确定
            EventBus.getDefault().post(new HRContactWayEvent(contactWay));//保存联系方式
            finish();//关闭页面
        }
    }
}
