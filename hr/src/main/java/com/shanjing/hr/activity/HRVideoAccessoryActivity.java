package com.shanjing.hr.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.BaseActivity;
import com.shanjing.hr.R;
import com.shanjing.hr.utils.FileUtil;

import java.io.File;

/**
 * 视频附件
 */
public class HRVideoAccessoryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_hr_back, ll_menu;
    private TextView tv_title, tv_upload_video;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrvideo_accessory);
        initView();
    }

    private void initView() {
        ll_hr_back = findViewById(R.id.ll_hr_back);
        tv_title = findViewById(R.id.tv_title);
        ll_menu = findViewById(R.id.ll_menu);
        tv_upload_video = findViewById(R.id.tv_upload_video);
        iv = findViewById(R.id.iv);
        ll_hr_back.setOnClickListener(this);
        tv_upload_video.setOnClickListener(this);
        tv_title.setText("视频附件");
        ll_menu.setVisibility(View.GONE);
    }

    private static int REQUEST_VIDEO_CODE = 1;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_hr_back) {
            finish();
        } else if (i == R.id.tv_upload_video) {//选择视频
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_VIDEO_CODE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                // 视频路径
                final String videoPath = FileUtil.getPath(this, uri);
                // 视频大小
                long videoSize = 0;
                try {
                    videoSize = FileUtil.getFileSize(new File(videoPath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("aaa", "onActivityResult: "+"videoPath== " + videoPath + " videoSize== " + videoSize);
                //转换文件大小类型
                if (videoSize > 20) {
                    return;
                }
                // 通过视频路径获取bitmap
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);
                iv.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
