package com.shanjing.hotattention.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.activity.OneCommentListActivity;
import com.shanjing.hotattention.activity.HelpActivity;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.AddCommentBean;
import com.shanjing.hotattention.bean.HomeBean;
import com.shanjing.hotattention.bean.HomeClassifyBean;
import com.shanjing.hotattention.utils.AddFriendPopWindow;
import com.shanjing.hotattention.utils.HotHomePopWindow;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 首页右侧内容适配器
 */
public class RecycleHomeRightAdapter extends RecyclerView.Adapter<RecycleHomeRightAdapter.ViewHolder> {

    private Context context;
    private List<HomeBean.DataBean> list;
    private GalleryAdapter galleryAdapter;
    private TextView tv_send;
    private boolean isShowOrNot = false;

    public RecycleHomeRightAdapter(Context context, List<HomeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecycleHomeRightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_list_item3, null, false);
        RecycleHomeRightAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private LoadingDialog loadingDialog;
    Gson gson = new Gson();

    @Override
    public void onBindViewHolder(@NonNull final RecycleHomeRightAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_content.setText(setTextView(list.get(i).getContent(), viewHolder.tv_content));

        /**
         * 视屏适配器
         */
        String str = list.get(i).getMedia_url().replace(" ", "");//去掉所有空格
        List<String> list2 = new ArrayList<>(Arrays.asList((str.split("[|]"))));

        String str2 = list.get(i).getList_photo().replace(" ", "");//去掉所有空格
        List<String> list_photo = new ArrayList<>(Arrays.asList((str2.split("[|]"))));
        //设置视频布局管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        galleryAdapter = new GalleryAdapter(context, list2, list_photo);
        viewHolder.recyclerView.setAdapter(galleryAdapter);

        /**
         * 评论适配器
         */
        viewHolder.judgeListsBeans.clear();
        viewHolder.judgeListsBeans.addAll(list.get(i).getJudgeLists());
        StaggeredGridLayoutManager linearLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        viewHolder.rv_comment.setLayoutManager(linearLayoutManager2);
        //设置适配器
        viewHolder.recyclerCommentAdapter = new RecyclerCommentAdapter(context, viewHolder.judgeListsBeans);
        viewHolder.rv_comment.setAdapter(viewHolder.recyclerCommentAdapter);

        //是否隐藏更多
        if (viewHolder.judgeListsBeans.size() >= 3) {
            viewHolder.ll_more.setVisibility(View.VISIBLE);
        } else if (viewHolder.judgeListsBeans.size() < 3) {
            viewHolder.ll_more.setVisibility(View.GONE);
        }


        /**
         * 更多点击事件
         */
        viewHolder.ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OneCommentListActivity.class);
                intent.putExtra("news_id", "" + list.get(i).getId());
                context.startActivity(intent);
            }
        });

        /**
         * 问答按钮的点击
         */
        /*viewHolder.btn_q_and_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转到问答首页
                context.startActivity(new Intent(context, HelpActivity.class));
            }
        });*/

        if (viewHolder.tv_content.length() < 75) {
            viewHolder.tv_qw.setText("");
        } else {
            //展开全文
            viewHolder.tv_qw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isShowOrNot) {
                        viewHolder.tv_content.setMaxLines(3);
                        viewHolder.tv_qw.setText("全文");
                        isShowOrNot = true;
                    } else {
                        viewHolder.tv_content.setMaxLines(30);
                        viewHolder.tv_qw.setText("收起");
                        isShowOrNot = false;
                    }
                }
            });
        }

        //加好友弹窗
        viewHolder.iv_add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddFriendPopWindow(context).showAtBottom(viewHolder.iv_add_friend);
            }
        });

        /**
         * 设置点击iv_pop弹窗
         */
        viewHolder.iv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HotHomePopWindow hotHomePopWindow = new HotHomePopWindow(context, list.get(i).getMember_id(), list.get(i).getId(),
                        list.get(i).getMember_id());

                hotHomePopWindow.showAtBottom(viewHolder.iv_pop);
                hotHomePopWindow.iv_write.setOnClickListener(new View.OnClickListener() {

                    private EditText et_context;
                    private View inflate;
                    private Dialog dialog;

                    @Override
                    public void onClick(View v) {
                        hotHomePopWindow.dismiss();//关闭PopupWindow
                        dialog = new Dialog(context, R.style.ActionSheetDialogStyle2);
                        //填充对话框的布局
                        inflate = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
                        //获取控件
                        et_context = inflate.findViewById(R.id.et_context);
                        tv_send = inflate.findViewById(R.id.tv_send);
                        //发送评论的点击事件
                        tv_send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String text = et_context.getText().toString();
                                HashMap<String, String> paramsMap = new HashMap<>();
                                paramsMap.put("member_id", "" + list.get(i).getMember_id());//用户ID
                                paramsMap.put("news_id", "" + list.get(i).getId());//内容ID
                                paramsMap.put("context", "" + text);//评价内容
                                paramsMap.put("pid", "" + list.get(i).getMember_id());//被评价者ID
                                paramsMap.put("create_by", "" + list.get(i).getMember_id());//内容用户ID
                                String info = gson.toJson(paramsMap);
                                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                                RxHttpUtils
                                        .createApi(HotService.class)
                                        .addComment(body)
                                        .compose(Transformer.<AddCommentBean>switchSchedulers(loadingDialog))
                                        .subscribe(new Observer<AddCommentBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(AddCommentBean addCommentBean) {
                                                ToastUtil.showShort(context, addCommentBean.getMsg());
                                                dialog.dismiss();
                                                HomeBean.DataBean.JudgeListsBean judgeListsBean = new HomeBean.DataBean.JudgeListsBean();
                                                judgeListsBean.setCreate_date(""+addCommentBean.getData().getCreate_date());
                                                judgeListsBean.setContext(""+addCommentBean.getData().getContext());
                                                viewHolder.recyclerCommentAdapter.addComment(judgeListsBean);
                                                viewHolder.recyclerCommentAdapter.notifyDataSetChanged();

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
                        });

                        //et_context输入内容监听
                        et_context.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (TextUtils.isEmpty(et_context.getText())) {
                                    tv_send.setBackgroundResource(R.drawable.shape_btn_bg);
                                    tv_send.setTextColor(context.getResources().getColor(R.color.color_999999));
                                } else {
                                    tv_send.setBackgroundResource(R.drawable.shape_btn_bg_blue);
                                    tv_send.setTextColor(context.getResources().getColor(R.color.white));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
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
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_content, tv_qw;
        private ImageView iv_add_friend, iv_pop;
        private Button btn_q_and_a;
        private LinearLayout ll_more;
        private List<HomeBean.DataBean.JudgeListsBean> judgeListsBeans = new ArrayList<>();
        private RecyclerCommentAdapter recyclerCommentAdapter;
        private RecyclerView recyclerView, rv_comment;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_qw = itemView.findViewById(R.id.tv_qw);
            iv_add_friend = itemView.findViewById(R.id.iv_add_friend);
            iv_pop = itemView.findViewById(R.id.iv_pop);
            recyclerView = itemView.findViewById(R.id.rv);
            rv_comment = itemView.findViewById(R.id.rv_comment);
            btn_q_and_a = itemView.findViewById(R.id.btn_q_and_a);
            ll_more = itemView.findViewById(R.id.ll_more);
        }
    }

    //截取话题进行染色
    private CharSequence setTextView(String desc, TextView tv) {
        /*正则表达式  取出 两个#之间的内容 （不包含#） */
        Pattern p = Pattern.compile("#([^\\#|.]+)#");
        /*android 提供的 具有强大的CharSequence 处理能力 各种区域处理*/
        SpannableString ss = new SpannableString(desc);
        Matcher m = p.matcher(desc);
        /*由于@昵称、#话题#、http://等这些关键字是可以点击的，所以我们需要对TextView做一些处理，需要去设置它的MovementMethod*/
        if (m.find()) {
            // 要实现文字的点击效果，这里需要做特殊处理
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            // 重置正则位置
            m.reset();
        }
        /*循环找出每个复合正则的字符串，逐个处理*/
        while (m.find()) {
            /*取出 字符串 前后添加#*/
            final String s = "#" + m.group(1) + "#";
            int startIndex = m.start(1) - 1;
            int endIndex = startIndex + s.length();
            /*区域处理*/

            ss.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    //        super.updateDrawState(ds);
                    //    按自己需求    重写 父类方法.
                    //        字体变色
                    ds.setColor(Color.parseColor("#507daf"));
                    //            设置下划线
                    ds.setUnderlineText(false);
                }
            }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

}
