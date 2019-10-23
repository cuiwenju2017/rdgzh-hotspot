package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.Collections;
import java.util.List;

/**
 * 求职适配器
 */
public class JobWantedAdapter extends RecyclerView.Adapter<JobWantedAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list, list2;

    public JobWantedAdapter(Context context, List<String> list, List<String> list2) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.list2 = list2;
    }

    public interface OnItemClickListener {
        void onItemClick(List<String> holder, int pos);
    }

    private JobWantedAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(JobWantedAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public JobWantedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_wanted_job, null, false);
        JobWantedAdapter.ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_username = view.findViewById(R.id.tv_username);
        viewHolder.tv_sex = view.findViewById(R.id.tv_sex);
        viewHolder.tv_look = view.findViewById(R.id.tv_look);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final JobWantedAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_username.setText(list.get(i));
        viewHolder.tv_sex.setText(list2.get(i));
        //查看的点击事件
        viewHolder.tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(Collections.singletonList(list.get(viewHolder.getLayoutPosition())), viewHolder.getLayoutPosition());
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView tv_username, tv_sex, tv_look;
    }
}
