package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shanjing.hotattention.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 首页视频适配器
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list2, list_photo;

    public GalleryAdapter(Context context, List<String> list2, List<String> list_photo) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list2 = list2;
        this.list_photo = list_photo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        JCVideoPlayerStandard videoplayer;
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_hot_rv,
                null, false);
        GalleryAdapter.ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.videoplayer = view.findViewById(R.id.videoplayer);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
        boolean setUp = viewHolder.videoplayer.setUp(list2.get(i), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(list_photo.get(i))
                    .into(viewHolder.videoplayer.thumbImageView);
        }
    }
}