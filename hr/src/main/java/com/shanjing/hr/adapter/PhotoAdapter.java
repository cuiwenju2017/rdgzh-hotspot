package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.shanjing.hr.R;

import java.util.List;

/**
 * 招聘图片适配器
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context context;
    private List<String> listphoto;

    public PhotoAdapter(Context context, List<String> listphoto) {
        this.context = context;
        this.listphoto = listphoto;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, null, false);
        PhotoAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(listphoto.get(i)).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return listphoto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
