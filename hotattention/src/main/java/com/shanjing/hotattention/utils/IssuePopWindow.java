package com.shanjing.hotattention.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.activity.IssueActivity;


public class IssuePopWindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private ImageView iv;
    private TextView tv_questions_and_answers, tv_hotattention, tv_help;

    public IssuePopWindow(Context context, ImageView iv) {
        this.context = context;
        this.iv = iv;
        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pop_issue, null);
        tv_questions_and_answers = view.findViewById(R.id.tv_questions_and_answers);
        tv_hotattention = view.findViewById(R.id.tv_hotattention);
        tv_help = view.findViewById(R.id.tv_help);
        tv_questions_and_answers.setOnClickListener(this);
        tv_hotattention.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.35));
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.7f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
                // 旋转
                RotateAnimation rAnim = new RotateAnimation(45, 0,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rAnim.setDuration(3 * 100);
                //动画完成后，是否保持
                rAnim.setFillAfter(true);
                iv.startAnimation(rAnim);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, -100, -650);
    }

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_questions_and_answers) {//点击问答跳转到问答的发布页
            dismiss();
            Intent intent = new Intent(context, IssueActivity.class);
            sprfMain = context.getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("choose_issue", "" + tv_questions_and_answers.getText());
            editorMain.commit();
            context.startActivity(intent);
        } else if (i == R.id.tv_hotattention) {//点击热点跳转到热点的发布页
            dismiss();
            Intent intent = new Intent(context, IssueActivity.class);
            sprfMain = context.getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("choose_issue", "" + tv_hotattention.getText());
            editorMain.commit();
            context.startActivity(intent);
        } else if (i == R.id.tv_help) {//点击帮忙跳转到帮忙的发布页
            dismiss();
            Intent intent = new Intent(context, IssueActivity.class);
            sprfMain = context.getSharedPreferences("counter", Context.MODE_PRIVATE);
            editorMain = sprfMain.edit();
            editorMain.putString("choose_issue", "" + tv_help.getText());
            editorMain.commit();
            context.startActivity(intent);
        }
    }
}
