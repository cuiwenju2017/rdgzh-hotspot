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
import android.widget.TextView;

import com.base.commonlib.view.LoadingDialog;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.activity.HelpActivity;
import com.shanjing.hotattention.bean.LikeListBean;
import com.shanjing.hotattention.utils.AddFriendPopWindow;
import com.shanjing.hotattention.utils.HotHomePopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我的点赞适配器
 */
public class RecycleLikeAdapter extends RecyclerView.Adapter<RecycleLikeAdapter.ViewHolder> {

    private Context context;
    private List<LikeListBean.DataBean> list;
    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;

    public RecycleLikeAdapter(Context context, List<LikeListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecycleLikeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_like, null, false);
        RecycleLikeAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private LoadingDialog loadingDialog;

    @Override
    public void onBindViewHolder(@NonNull final RecycleLikeAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_content.setText(list.get(i).getContent());
        /**
         * 视屏适配器
         */
        String str = list.get(i).getMediaurl().replace(" ", "");//去掉所有空格
        List<String> list2 = new ArrayList<>(Arrays.asList((str.split("[|]"))));

        String str2 = list.get(i).getPhoto().replace(" ", "");//去掉所有空格
        List<String> list_photo = new ArrayList<>(Arrays.asList((str2.split("[|]"))));
        //设置视频布局管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        galleryAdapter = new GalleryAdapter(context, list2, list_photo);
        recyclerView.setAdapter(galleryAdapter);

        /**
         * 设置点击iv_pop弹窗
         */
        viewHolder.iv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HotHomePopWindow(context, list.get(i).getMember_id(), list.get(i).getNews_id(),
                        list.get(i).getMember_id()).showAtBottom(viewHolder.iv_pop);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private boolean isShowOrNot = false;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_content, tv_qw;
        private ImageView iv_add_friend, iv_pop;
        private Button btn_q_and_a;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_qw = itemView.findViewById(R.id.tv_qw);
            iv_add_friend = itemView.findViewById(R.id.iv_add_friend);
            iv_pop = itemView.findViewById(R.id.iv_pop);
            recyclerView = itemView.findViewById(R.id.rv);
            btn_q_and_a = itemView.findViewById(R.id.btn_q_and_a);
            /**
             * 问答按钮的点击
             */
            btn_q_and_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//跳转到问答首页
                    context.startActivity(new Intent(context, HelpActivity.class));
                }
            });

            //展开全文
            tv_qw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShowOrNot == false) {
                        tv_content.setMaxLines(3);
                        tv_qw.setText("全文");
                        isShowOrNot = true;
                    } else {
                        tv_content.setMaxLines(10);
                        tv_qw.setText("收起");
                        isShowOrNot = false;
                    }
                }
            });
            //加好友弹窗
            iv_add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AddFriendPopWindow(context).showAtBottom(iv_add_friend);
                }
            });

        }
    }

}
