package com.shanjing.hr.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;

import java.io.File;

/**
 * 附件简历
 */
public class HRAttachmentResumeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title, tv_choose_attachment_resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrattachment_resume);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        tv_choose_attachment_resume = findViewById(R.id.tv_choose_attachment_resume);
        ll_hr_back.setOnClickListener(this);
        tv_choose_attachment_resume.setOnClickListener(this);
        tv_title.setText("附件简历");
        ll_menu.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.tv_choose_attachment_resume) {//选择附件简历

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            try {
                startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 345);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
