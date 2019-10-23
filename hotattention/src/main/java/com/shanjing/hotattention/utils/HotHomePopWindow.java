package com.shanjing.hotattention.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.AddCollectBean;
import com.shanjing.hotattention.bean.AddLikeBean;
import com.shanjing.hotattention.bean.DelectCollectBean;
import com.shanjing.hotattention.bean.DelectLikeBean;
import com.shanjing.hotattention.bean.HomeBean;
import com.shanjing.hotattention.bean.IsCollectBean;
import com.shanjing.hotattention.bean.IsLikeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 评论、点赞、收藏、分享PopupWindow弹窗
 */
public class HotHomePopWindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private ImageView iv_collect, iv_share;
    public ImageView iv_write, iv_like;
    private Dialog dialog;
    private View inflate;
    private String member_id, news_id, create_by;

    public HotHomePopWindow(Context context, String member_id, String news_id, String create_by) {
        super(context);
        this.context = context;
        this.member_id = member_id;
        this.news_id = news_id;
        this.create_by = create_by;
        initalize();
        initData();
    }

    private void initData() {
        /**
         * 是否点赞
         */
        RxHttpUtils
                .createApi(HotService.class)
                .isLike("" + member_id, "" + news_id, "" + create_by)
                .compose(Transformer.<IsLikeBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<IsLikeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IsLikeBean isLikeBean) {
                        if ("200".equals(isLikeBean.getCode())) {
                            iv_like.setBackgroundResource(R.drawable.ic_like);
                        } else if ("2".equals(isLikeBean.getCode())) {
                            iv_like.setBackgroundResource(R.drawable.ic_like);
                        } else if ("1".equals(isLikeBean.getCode())) {
                            iv_like.setBackgroundResource(R.drawable.ic_like_active);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(context, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /**
         * 是否收藏
         */
        RxHttpUtils
                .createApi(HotService.class)
                .isCollect(member_id, news_id, create_by)
                .compose(Transformer.<IsCollectBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<IsCollectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IsCollectBean isCollectBean) {
                        if ("200".equals(isCollectBean.getCode())) {
                            iv_collect.setBackgroundResource(R.drawable.ic_collect);
                        } else if ("2".equals(isCollectBean.getCode())) {
                            iv_collect.setBackgroundResource(R.drawable.ic_collect);
                        } else if ("1".equals(isCollectBean.getCode())) {
                            iv_collect.setBackgroundResource(R.drawable.ic_collect_active);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(context, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_dialog_hot, null);
        iv_write = view.findViewById(R.id.iv_write);//评论
        iv_like = view.findViewById(R.id.iv_like);//喜欢
        iv_collect = view.findViewById(R.id.iv_collect);//收藏
        iv_share = view.findViewById(R.id.iv_share);//分享
        iv_write.setOnClickListener(this);
        iv_like.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.4));
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);//设置
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 1.0f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
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
        showAsDropDown(view, -460, -70);
    }

    private LoadingDialog loadingDialog;
    Gson gson = new Gson();
    private EditText et_context;
    private List<HomeBean.DataBean> list = new ArrayList<>();

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_like) {//点赞
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("member_id", "" + member_id);//用户ID
            paramsMap.put("news_id", "" + news_id);//内容ID
            paramsMap.put("create_by", "" + create_by);//内容用户ID
            String info = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
            RxHttpUtils
                    .createApi(HotService.class)
                    .addLike(body)
                    .compose(Transformer.<AddLikeBean>switchSchedulers(loadingDialog))
                    .subscribe(new Observer<AddLikeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddLikeBean addLikeBean) {
                            if ("success".equals(addLikeBean.getStatus())) {
                                iv_like.setBackgroundResource(R.drawable.ic_like_active);
                            } else if ("error".equals(addLikeBean.getStatus())) {
                                iv_like.setBackgroundResource(R.drawable.ic_like_active);
                                //取消点赞
                                RxHttpUtils
                                        .createApi(HotService.class)
                                        .deleteLike("" + addLikeBean.getData().get(0).getId())
                                        .compose(Transformer.<DelectLikeBean>switchSchedulers(loadingDialog))
                                        .subscribe(new Observer<DelectLikeBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(DelectLikeBean delectLikeBean) {
                                                if ("success".equals(delectLikeBean.getStatus())) {
                                                    iv_like.setBackgroundResource(R.drawable.ic_like);
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                ToastUtil.showShort(context, "" + e);
                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showShort(context, "" + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else if (i == R.id.iv_collect) {//收藏
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("member_id", "" + member_id);//用户ID
            paramsMap.put("resouce_id", "" + news_id);//内容ID
            paramsMap.put("create_by", "" + create_by);//内容用户ID
            String info = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
            RxHttpUtils
                    .createApi(HotService.class)
                    .addCollect(body)
                    .compose(Transformer.<AddCollectBean>switchSchedulers(loadingDialog))
                    .subscribe(new Observer<AddCollectBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AddCollectBean addCollectBean) {
                            if ("success".equals(addCollectBean.getStatus())) {
                                iv_collect.setBackgroundResource(R.drawable.bt_mycollection);
                            } else if ("error".equals(addCollectBean.getStatus())) {
                                iv_collect.setBackgroundResource(R.drawable.bt_mycollection);
                                //取消收藏
                                RxHttpUtils
                                        .createApi(HotService.class)
                                        .deleteCollect("" + addCollectBean.getData().get(0).getId())
                                        .compose(Transformer.<DelectCollectBean>switchSchedulers(loadingDialog))
                                        .subscribe(new Observer<DelectCollectBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(DelectCollectBean delectCollectBean) {
                                                iv_collect.setBackgroundResource(R.drawable.ic_collect);
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                ToastUtil.showShort(context, "" + e);
                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.showShort(context, "" + e);
                            Log.i("aaa", "onError: " + e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else if (i == R.id.iv_share) {//分享弹窗
            dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            inflate = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
            //获取控件
            TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
            LinearLayout ll_wx = inflate.findViewById(R.id.ll_wx);
            LinearLayout ll_qq = inflate.findViewById(R.id.ll_qq);
            LinearLayout ll_wb = inflate.findViewById(R.id.ll_wb);
            LinearLayout ll_pyq = inflate.findViewById(R.id.ll_pyq);
            LinearLayout ll_copy_link = inflate.findViewById(R.id.ll_copy_link);
            //获取监听
            tv_cancel.setOnClickListener(this);
            ll_wx.setOnClickListener(this);
            ll_qq.setOnClickListener(this);
            ll_wb.setOnClickListener(this);
            ll_pyq.setOnClickListener(this);
            ll_copy_link.setOnClickListener(this);
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
        } else if (i == R.id.tv_cancel) {
            dialog.dismiss();
        } else if (i == R.id.ll_wx) {
            Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_qq) {
            Toast.makeText(context, "QQ", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_wb) {
            Toast.makeText(context, "微博", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_pyq) {
            Toast.makeText(context, "朋友圈", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.ll_copy_link) {
            Toast.makeText(context, "复制链接", Toast.LENGTH_SHORT).show();
        }
    }
}