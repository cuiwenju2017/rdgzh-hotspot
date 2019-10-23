package com.shanjing.hotattention.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.bumptech.glide.Glide;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.photoPicker.ImageLoader;
import com.shanjing.hotattention.photoPicker.ImgSelActivity;
import com.shanjing.hotattention.photoPicker.ImgSelConfig;
import com.shanjing.hotattention.utils.ImageLoaderUtils;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 编辑资料
 */
public class EditDataActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private TextView tv_title_name;
    private RelativeLayout rl_head;
    private CircleImageView civ_head;
    private int REQUEST_CODE = 120;
    private RelativeLayout rl_user_name;
    private EditText et_username;
    private TextView tv_num_text, tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        initView();
        initData();
    }

    private void initData() {
        tv_title_name.setText("编辑资料");
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title_name = findViewById(R.id.tv_title_name);
        rl_head = findViewById(R.id.rl_head);
        civ_head = findViewById(R.id.civ_head);
        rl_user_name = findViewById(R.id.rl_user_name);
        tv_username = findViewById(R.id.tv_username);
        ll_back.setOnClickListener(this);
        rl_head.setOnClickListener(this);
        rl_user_name.setOnClickListener(this);
    }

    private Dialog dialog;
    private View inflate;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {//返回
            finish();
        } else if (i == R.id.rl_head) {//头像选择
            //去寻找是否已经有了相机的权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                ImgSelConfig config = new ImgSelConfig.Builder(loader)
                        // 是否多选
                        .multiSelect(false)
                        // 确定按钮背景色
                        .btnBgColor(Color.TRANSPARENT)
                        .titleBgColor(ContextCompat.getColor(this, R.color.colorBule))
                        // 使用沉浸式状态栏
                        .statusBarColor(ContextCompat.getColor(this, R.color.colorBule))
                        // 返回图标ResId
                        .backResId(R.drawable.ic_back_x)
                        .title("图片")
                        // 第一个是否显示相机
                        .needCamera(true)
                        //按钮字体颜色
                        .btnTextColor(ContextCompat.getColor(this, R.color.colorBule))
                        .build();
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);
            } else {
                //否则去请求相机权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
            }

        } else if (i == R.id.rl_user_name) {//编辑用户名
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle2);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.dialog_edit_username, null);
            //获取控件
            Button btn_cancel = inflate.findViewById(R.id.btn_cancel);
            et_username = inflate.findViewById(R.id.et_username);
            tv_num_text = inflate.findViewById(R.id.tv_num_text);
            Button btn_confirm = inflate.findViewById(R.id.btn_confirm);
            //进入页面显示输入字数
            tv_num_text.setText(et_username.getText().toString().length() + "/10");
            //弹窗的用户名随外边的用户名变化
            et_username.setText(tv_username.getText().toString());
            /**
             * 获取et_username编辑框的焦点
             */
            et_username.setFocusable(true);
            et_username.setFocusableInTouchMode(true);
            et_username.requestFocus();
            /**
             * et_username输入监听与限制
             */
            et_username.addTextChangedListener(new TextWatcher() {

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
                    editStart = et_username.getSelectionStart();
                    editEnd = et_username.getSelectionEnd();
                    tv_num_text.setText(temp.length() + "/10");//输入后字数显示
                    if (temp.length() > 10) {//输入字数限制
                        s.delete(editStart - 1, editEnd);//删除限制外的内容
                        int tempSelection = editStart;
                        et_username.setText(s);//显示限制内的内容
                        et_username.setSelection(tempSelection);//光标焦点设置在行末
                    }
                }
            });

            //获取监听
            btn_cancel.setOnClickListener(this);
            btn_confirm.setOnClickListener(this);
            //将布局设置给Dialog
            dialog.setContentView(inflate);
            //获取当前Activity所在的窗体
            Window dialogWindow = dialog.getWindow();
            //设置Dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.y = 0;//设置Dialog距离底部的距离
            //宽度填满
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
            dialog.show();//显示对话框
        } else if (i == R.id.btn_cancel) {//取消弹窗
            dialog.dismiss();
        } else if (i == R.id.btn_confirm) {//确定按钮
            tv_username.setText(et_username.getText().toString());
            dialog.dismiss();
        }
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.display(context, imageView, path);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            Glide.with(this).load(pathList.get(0)).into(civ_head);//显示图片
        }
    }

}
