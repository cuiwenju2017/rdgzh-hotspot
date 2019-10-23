package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.Collections;
import java.util.List;

/**
 * 公司规模适配器
 */
public class HRScaleAdapter extends RecyclerView.Adapter<HRScaleAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public HRScaleAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(List<String> holder, int pos);
    }

    private HRScaleAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(HRScaleAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public HRScaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hr_financing, null, false);
        HRScaleAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HRScaleAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(list.get(i));
        if (i == myposition) {//设置选中item的字体颜色
            viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.hr_white));
            viewHolder.tv_name.setBackgroundResource(R.drawable.shape_tv_bg_pink);
        } else {
            viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.color_hr_red));
            viewHolder.tv_name.setBackgroundResource(R.drawable.shape_tv_pink);
        }
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRangeChanged(0, list.size());
                if (mListener != null) {
                    mListener.onItemClick(Collections.singletonList(list.get(viewHolder.getLayoutPosition())), viewHolder.getLayoutPosition());
                }
            }
        });
    }

    //获取当前的索引
    int myposition;

    public void getIndex(int myposition) {
        this.myposition = myposition;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
