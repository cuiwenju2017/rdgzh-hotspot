package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.shanjing.hr.R;
import com.shanjing.hr.utils.HRHomePopWindow;
import java.util.Arrays;
import java.util.List;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * 推荐适配器
 */
public class HRRecommendAdapter extends RecyclerView.Adapter<HRRecommendAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list;

    public HRRecommendAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public HRRecommendAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_recommend, null, false);
        HRRecommendAdapter.ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_username = view.findViewById(R.id.tv_username);
        viewHolder.videoplayer = view.findViewById(R.id.videoplayer);
        viewHolder.iv_pop = view.findViewById(R.id.iv_pop);
        viewHolder.rv_comment = view.findViewById(R.id.rv_comment);
        return viewHolder;
    }

    private List<String> listComment = Arrays.asList("张小菲", "HR");

    @Override
    public void onBindViewHolder(final HRRecommendAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_username.setText(list.get(i));

        boolean setUp = viewHolder.videoplayer.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565266227642&di=826df1af5cc3827f7bf83fdcad3178a9&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F5d0178fceb23bf6ff141fdb6f9a5aa8e030f6f8b119b9c-nQHjkQ_fw658")
                    .into(viewHolder.videoplayer.thumbImageView);
        }

        //iv_pop弹窗
        viewHolder.iv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HRHomePopWindow hrHomePopWindow = new HRHomePopWindow(context);
                hrHomePopWindow.showAtBottom(viewHolder.iv_pop);
            }
        });

        //评论适配器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        viewHolder.rv_comment.setLayoutManager(linearLayoutManager);
        CommentAdapter commentAdapter = new CommentAdapter(context, listComment);
        viewHolder.rv_comment.setAdapter(commentAdapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View arg0) {
            super(arg0);
        }

        JCVideoPlayerStandard videoplayer;
        TextView tv_username;
        ImageView iv_pop;
        RecyclerView rv_comment;
    }
}
