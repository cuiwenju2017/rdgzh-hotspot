package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.activity.HelpActivity;
import com.shanjing.hotattention.activity.QuestionsAndAnswersActivity;
import com.shanjing.hotattention.bean.SearchListBean;
import com.shanjing.hotattention.utils.AddFriendPopWindow;
import com.shanjing.hotattention.utils.HotHomePopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 搜索适配器
 */
public class RecycleSearchAdapter extends RecyclerView.Adapter<RecycleSearchAdapter.ViewHolder> {

    private Context context;
    private List<SearchListBean.DataBean> list;
    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private boolean isShowOrNot = false;

    public RecycleSearchAdapter(Context context, List<SearchListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public UpdataMessage UpdataMessage;

    public interface UpdataMessage {
        void updata(boolean message);
    }

    public void updatadingd(UpdataMessage UpdataMessage) {
        this.UpdataMessage = UpdataMessage;
    }

    @NonNull
    @Override
    public RecycleSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_like, null, false);
        RecycleSearchAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private LoadingDialog loadingDialog;
    Gson gson = new Gson();

    @Override
    public void onBindViewHolder(@NonNull final RecycleSearchAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_content.setText(list.get(i).getSummary());

        if (list.size() > 1) {
            /**
             * 视屏适配器
             */
            String str = list.get(i).getMedia_url().replace(" ", "");//去掉所有空格
            List<String> list2 = new ArrayList<>(Arrays.asList((str.split("[|]"))));
            //封面图
            String str2 = list.get(i).getList_photo().replace(" ", "");//去掉所有空格
            List<String> list_photo = new ArrayList<>(Arrays.asList((str2.split("[|]"))));
            //设置视频布局管理器
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
            recyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            galleryAdapter = new GalleryAdapter(context, list2, list_photo);
            recyclerView.setAdapter(galleryAdapter);
        }

        if (viewHolder.tv_content.length() < 75) {//字数过少隐藏全文二字
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
                new HotHomePopWindow(context, list.get(i).getMember_id(), list.get(i).getId(),
                        list.get(i).getMember_id()).showAtBottom(viewHolder.iv_pop);
            }
        });

        //显示文章类型
        if ("answer".equals(list.get(i).getType())) {
            viewHolder.btn_q_and_a.setText("问答");
            //问答按钮的点击
            viewHolder.btn_q_and_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//跳转到问答首页
                    Intent intent = new Intent(context, QuestionsAndAnswersActivity.class);
                    intent.putExtra("id", list.get(i).getId());
                    context.startActivity(intent);
                }
            });
        } else if ("cmsnews".equals(list.get(i).getType())) {
            viewHolder.btn_q_and_a.setVisibility(View.GONE);
        } else if ("help".equals(list.get(i).getType())) {
            viewHolder.btn_q_and_a.setText("帮忙");
            viewHolder.btn_q_and_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//跳转到问答首页
                    Intent intent = new Intent(context, HelpActivity.class);
                    intent.putExtra("id", list.get(i).getId());
                    context.startActivity(intent);
                }
            });
        }
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

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_qw = itemView.findViewById(R.id.tv_qw);
            iv_add_friend = itemView.findViewById(R.id.iv_add_friend);
            iv_pop = itemView.findViewById(R.id.iv_pop);
            recyclerView = itemView.findViewById(R.id.rv);
            btn_q_and_a = itemView.findViewById(R.id.btn_q_and_a);
            ll_more = itemView.findViewById(R.id.ll_more);
        }
    }

}
