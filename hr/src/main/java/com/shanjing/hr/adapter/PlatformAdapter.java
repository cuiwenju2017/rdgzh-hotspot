package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.List;

/**
 * 平台适配器
 */
public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list;

    public PlatformAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public PlatformAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_platform, null, false);
        PlatformAdapter.ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_username = view.findViewById(R.id.tv_username);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final PlatformAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_username.setText(list.get(i));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView tv_username;
    }
}
