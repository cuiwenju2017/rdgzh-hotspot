package com.shanjing.hotattention.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.adapter.CheckRecycleAdapter;
import com.shanjing.hotattention.adapter.NinePicturesAdapter;
import com.shanjing.hotattention.adapter.RadioRecycleAdapter;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.AddArticleBean;
import com.shanjing.hotattention.bean.AddHelpBean;
import com.shanjing.hotattention.bean.AddQAABean;
import com.shanjing.hotattention.bean.HomeClassifyBean;
import com.shanjing.hotattention.photoPicker.ImageLoader;
import com.shanjing.hotattention.photoPicker.ImgSelActivity;
import com.shanjing.hotattention.photoPicker.ImgSelConfig;
import com.shanjing.hotattention.utils.ImageLoaderUtils;
import com.shanjing.hotattention.view.NoScrollGridView;
import com.xsm.library.TEditText;
import com.xsm.library.TObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 发布
 */
public class IssueActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_back;
    private NoScrollGridView gridview;
    private NinePicturesAdapter ninePicturesAdapter;
    private int REQUEST_CODE = 120;
    private Dialog dialog;
    private TextView tv_picture, tv_video, tv_issue;
    private TEditText et_context;
    private LinearLayout ll_topic, ll_link_man, ll_tall_choose;
    private RelativeLayout rl_channel_choose, rl_tall_choose;
    private View inflate;
    private TextView tv_channel;
    private RecyclerView rv_advanced_options;
    private ImageView iv_show;
    private String choose_issue, topicname, topicid;
    SharedPreferences sprfMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        Intent intent = getIntent();
        sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
        choose_issue = sprfMain.getString("choose_issue", "");//选择发布的类别
        topicname = intent.getStringExtra("topicname");//话题名称
        topicid = intent.getStringExtra("topicid");//话题ID
        initView();
        initData();
    }

    Gson gson = new Gson();
    private LoadingDialog loadingDialog;

    private void initData() {
        //发布内容监听
        et_context.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tv_issue.setBackgroundResource(R.drawable.shape_btn_bg);
                    tv_issue.setTextColor(getResources().getColor(R.color.color_999999));
                    tv_issue.setOnClickListener(null);//清空tv_issue点击事件
                } else if (!TextUtils.isEmpty(s)) {
                    tv_issue.setBackgroundResource(R.drawable.shape_btn_bg_blue);
                    tv_issue.setTextColor(getResources().getColor(R.color.white));
                    tv_issue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if ("问答".equals(choose_issue)) {
                                if ("".equals(tv_channel.getText().toString())) {
                                    Toast.makeText(IssueActivity.this, "请选择分类", Toast.LENGTH_SHORT).show();
                                } else if ("".equals(topicid) | topicid == null) {
                                    Toast.makeText(IssueActivity.this, "请选择话题", Toast.LENGTH_SHORT).show();
                                } else {
                                    //发布问答
                                    HashMap<String, String> paramsMap = new HashMap<>();
                                    paramsMap.put("category_id", category_id);//分类ID
                                    paramsMap.put("title", "文章标题");//文章标题
                                    paramsMap.put("source_from", "app");//文章来源
                                    paramsMap.put("list_photo", "http://pic46.nipic.com/20140817/7144451_144052790000_2.jpg");//封面图地址
                                    paramsMap.put("media_url", "http://vjs.zencdn.net/v/oceans.mp4");//视频地址
                                    paramsMap.put("create_by", "15767952761");//创建者ID
                                    paramsMap.put("content", "" + s);//文章内容
                                    paramsMap.put("member_id", "15767952761");//用户ID
                                    paramsMap.put("topic_id", topicid);//话题ID
                                    String info = gson.toJson(paramsMap);
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                                    RxHttpUtils
                                            .createApi(HotService.class)
                                            .addQAA(body)
                                            .compose(Transformer.<AddQAABean>switchSchedulers(loadingDialog))
                                            .subscribe(new Observer<AddQAABean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(AddQAABean addQAABean) {
                                                    ToastUtil.showShort(IssueActivity.this, addQAABean.getMsg());
                                                    finish();
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    ToastUtil.showShort(IssueActivity.this, "" + e);
                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });
                                }
                            } else if ("热点".equals(choose_issue)) {
                                if ("".equals(tv_channel.getText().toString())) {
                                    Toast.makeText(IssueActivity.this, "请选择分类", Toast.LENGTH_SHORT).show();
                                } else if ("".equals(topicid) | topicid == null) {
                                    Toast.makeText(IssueActivity.this, "请选择话题", Toast.LENGTH_SHORT).show();
                                } else {
                                    //发布热点
                                    HashMap<String, String> paramsMap = new HashMap<>();
                                    paramsMap.put("category_id", category_id);//分类ID
                                    paramsMap.put("title", "文章标题");//文章标题
                                    paramsMap.put("source_from", "app");//文章来源
                                    paramsMap.put("list_photo", "http://pic46.nipic.com/20140817/7144451_144052790000_2.jpg");//封面图地址
                                    paramsMap.put("media_url", "http://vjs.zencdn.net/v/oceans.mp4");//视频地址
                                    paramsMap.put("create_by", "15767952761");//创建者ID
                                    paramsMap.put("content", "" + s);//文章内容
                                    paramsMap.put("member_id", "15767952761");//用户ID
                                    paramsMap.put("topic_id", topicid);//话题ID
                                    String info = gson.toJson(paramsMap);
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                                    RxHttpUtils
                                            .createApi(HotService.class)
                                            .addArticle(body)
                                            .compose(Transformer.<AddArticleBean>switchSchedulers(loadingDialog))
                                            .subscribe(new Observer<AddArticleBean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(AddArticleBean addArticleBean) {
                                                    ToastUtil.showShort(IssueActivity.this, "发布成功");
                                                    finish();
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    ToastUtil.showShort(IssueActivity.this, "" + e);
                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });
                                }
                            } else if ("帮忙".equals(choose_issue)) {
                                if ("".equals(tv_channel.getText().toString())) {
                                    Toast.makeText(IssueActivity.this, "请选择分类", Toast.LENGTH_SHORT).show();
                                } else if ("".equals(topicid) | topicid == null) {
                                    Toast.makeText(IssueActivity.this, "请选择话题", Toast.LENGTH_SHORT).show();
                                } else {
                                    //发布帮忙
                                    HashMap<String, String> paramsMap = new HashMap<>();
                                    paramsMap.put("category_id", category_id);//分类ID
                                    paramsMap.put("title", "文章标题");//文章标题
                                    paramsMap.put("source_from", "app");//文章来源
                                    paramsMap.put("list_photo", "http://pic46.nipic.com/20140817/7144451_144052790000_2.jpg");//封面图地址
                                    paramsMap.put("media_url", "http://vjs.zencdn.net/v/oceans.mp4");//视频地址
                                    paramsMap.put("create_by", "15767952761");//创建者ID
                                    paramsMap.put("content", "" + s);//文章内容
                                    paramsMap.put("member_id", "15767952761");//用户ID
                                    paramsMap.put("topic_id", topicid);//话题ID
                                    String info = gson.toJson(paramsMap);
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                                    RxHttpUtils
                                            .createApi(HotService.class)
                                            .addHelp(body)
                                            .compose(Transformer.<AddHelpBean>switchSchedulers(loadingDialog))
                                            .subscribe(new Observer<AddHelpBean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(AddHelpBean addHelpBean) {
                                                    ToastUtil.showShort(IssueActivity.this, addHelpBean.getMsg());
                                                    finish();
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    ToastUtil.showShort(IssueActivity.this, "" + e);
                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });
                                }
                            }
                        }
                    });
                }
            }
        });

        //高级选项适配器设置
        CheckRecycleAdapter checkRecycleAdapter = new CheckRecycleAdapter(this, Arrays.asList(checkList));
        //item的点击事件
        checkRecycleAdapter.setOnItemClickListener(new CheckRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String holder, int pos) {
                ToastUtil.showShort(IssueActivity.this, "" + holder);
            }
        });
        ((SimpleItemAnimator) rv_advanced_options.getItemAnimator()).setSupportsChangeAnimations(false);//防止刷新闪屏
        rv_advanced_options.setLayoutManager(new GridLayoutManager(this, 3));
        rv_advanced_options.setAdapter(checkRecycleAdapter);
    }

    private void initView() {
        ll_back = findViewById(R.id.ll_back);
        gridview = findViewById(R.id.gridview);
        et_context = findViewById(R.id.et_context);
        tv_issue = findViewById(R.id.tv_issue);
        ll_topic = findViewById(R.id.ll_topic);
        ll_link_man = findViewById(R.id.ll_link_man);
        rl_channel_choose = findViewById(R.id.rl_channel_choose);
        tv_channel = findViewById(R.id.tv_channel);
        rv_advanced_options = findViewById(R.id.rv_advanced_options);
        rl_tall_choose = findViewById(R.id.rl_tall_choose);
        ll_tall_choose = findViewById(R.id.ll_tall_choose);
        iv_show = findViewById(R.id.iv_show);
        ll_back.setOnClickListener(this);
        ll_topic.setOnClickListener(this);
        ll_link_man.setOnClickListener(this);
        rl_channel_choose.setOnClickListener(this);
        rl_tall_choose.setOnClickListener(this);

        //选择图片适配器
        ninePicturesAdapter = new NinePicturesAdapter(this, 9, new NinePicturesAdapter.OnClickAddListener() {
            @Override
            public void onClickAdd(int positin) {
                dialogMedia();
            }
        });
        gridview.setAdapter(ninePicturesAdapter);

        //显示话题
        TObject object = new TObject();
        //匹配规则
        object.setObjectRule("#");
        //话题内容
        object.setObjectText(topicname);
        et_context.setObject(object);
    }

    private void dialogMedia() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_media, null);
        //获取组件
        tv_picture = contentView.findViewById(R.id.tv_picture);
        tv_video = contentView.findViewById(R.id.tv_video);
        //获取Dialog的监听
        tv_picture.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().densityDpi;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);//弹窗位置
        dialog.getWindow().setWindowAnimations(R.style.ActionSheetDialogStyle);//弹窗样式
        dialog.show();//显示弹窗
    }

    /**
     * 开启图片选择器
     */
    private void choosePhoto() {
        //去寻找是否已经有了相机的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            dialog.dismiss();
            ImgSelConfig config = new ImgSelConfig.Builder(loader)
                    // 是否多选
                    .multiSelect(true)
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
                    // 最大选择图片数量
                    .maxNum(18 - ninePicturesAdapter.getPhotoCount())
                    .build();
            ImgSelActivity.startActivity(this, config, REQUEST_CODE);
        } else {
            //否则去请求相机权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
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
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (ninePicturesAdapter != null) {
                ninePicturesAdapter.addAll(pathList);
            }
        }
    }

    private String[] valueList = new String[]{"推荐", "美食", "房产", "电影娱乐", "科技", "娱乐",
            "游戏", "求职招聘", "购物"};
    private String[] checkList = new String[]{"热点关注", "美食", "房产", "人力资源"};
    private boolean isShow = false;
    private List<HomeClassifyBean.DataBean> dataBeans = new ArrayList<>();
    private String category_id;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_back) {
            finish();
        } else if (i == R.id.tv_picture) {
            choosePhoto();//选择图片
        } else if (i == R.id.ll_topic) {//选择话题
            startActivity(new Intent(this, TopicActivity.class));
            finish();
        } else if (i == R.id.ll_link_man) {//选择联系人
            startActivity(new Intent(this, LinkmanActivity.class));
        } else if (i == R.id.rl_channel_choose) {//频道分类选择
            dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(this).inflate(R.layout.dialog_channel_choose, null);
            //获取控件
            final RecyclerView radio_rv = inflate.findViewById(R.id.radio_rv);
            LinearLayout ll_cancel = inflate.findViewById(R.id.ll_cancel);
            final Button btn_confirm = inflate.findViewById(R.id.btn_confirm);
            //获取分类
            RxHttpUtils
                    .createApi(HotService.class)
                    .getHomeClassifyData()
                    .compose(Transformer.<HomeClassifyBean>switchSchedulers(loadingDialog))
                    .subscribe(new Observer<HomeClassifyBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(HomeClassifyBean homeClassifyBean) {
                            dataBeans = homeClassifyBean.getData();
                            RadioRecycleAdapter radioAdapter = new RadioRecycleAdapter(IssueActivity.this, dataBeans);
                            radioAdapter.setOnItemClickListener(new RadioRecycleAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(final HomeClassifyBean.DataBean holder, final int pos) {//选项选择
                                    btn_confirm.setOnClickListener(new View.OnClickListener() {//确定按钮的点击
                                        @Override
                                        public void onClick(View v) {
                                            tv_channel.setText("" + holder.getCategory_name());//显示选择的频道
                                            category_id = holder.getId();//获取分类id
                                            dialog.dismiss();//关闭弹窗
                                        }
                                    });
                                }
                            });
                            ((SimpleItemAnimator) radio_rv.getItemAnimator()).setSupportsChangeAnimations(false);//防止刷新闪屏
                            radio_rv.setLayoutManager(new GridLayoutManager(IssueActivity.this, 3));
                            radio_rv.setAdapter(radioAdapter);

                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showShort(IssueActivity.this, "" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            //获取监听
            ll_cancel.setOnClickListener(this);
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
        } else if (i == R.id.ll_cancel) {//取消弹窗的点击
            dialog.dismiss();
        } else if (i == R.id.rl_tall_choose) {//高级选项的点击
            if (!isShow) {//显示布局
                ll_tall_choose.setVisibility(View.VISIBLE);
                iv_show.setImageResource(R.drawable.ic_up);
                isShow = true;
            } else {//隐藏布局
                ll_tall_choose.setVisibility(View.GONE);
                iv_show.setImageResource(R.drawable.ic_arrow_right);
                isShow = false;
            }
        }
    }
}
