package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.Collections;
import java.util.List;

/**
 * 招聘适配器
 */
public class GiveJobAdapter extends RecyclerView.Adapter<GiveJobAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> list;

    public GiveJobAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(List<String> holder, int pos);
    }

    private GiveJobAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(GiveJobAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public GiveJobAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_give_job, null, false);
        GiveJobAdapter.ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_position = view.findViewById(R.id.tv_position);
        viewHolder.ll = view.findViewById(R.id.ll);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GiveJobAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_position.setText(list.get(i));
        //招聘详情的点击事件
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
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

        TextView tv_position;
        LinearLayout ll;
    }
}
