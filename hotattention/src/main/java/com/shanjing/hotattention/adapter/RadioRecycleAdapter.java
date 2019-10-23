package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.bean.HomeClassifyBean;

import java.util.List;

/**
 * 频道分类适配器
 */
public class RadioRecycleAdapter extends RecyclerView.Adapter<RadioRecycleAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeClassifyBean.DataBean> dataBeans;
    private int mSelectedItem = -1;

    public interface OnItemClickListener {
        void onItemClick(HomeClassifyBean.DataBean holder, int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RadioRecycleAdapter(Context mContext, List<HomeClassifyBean.DataBean> dataBeans) {
        this.mContext = mContext;
        this.dataBeans = dataBeans;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_radio, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.radioButton.setText(dataBeans.get(position).getCategory_name());
        holder.radioButton.setChecked(position == mSelectedItem);
        holder.radioButton.setTag(position);

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = (int) v.getTag();
                notifyItemRangeChanged(0, dataBeans.size());
                if (mListener != null) {
                    mListener.onItemClick(dataBeans.get(holder.getLayoutPosition()), holder.getLayoutPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.radio_rb);
        }
    }
}
