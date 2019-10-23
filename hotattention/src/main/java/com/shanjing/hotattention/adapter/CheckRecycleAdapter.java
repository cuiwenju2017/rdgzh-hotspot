package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.shanjing.hotattention.R;

import java.util.List;

/**
 * 高级选项适配器
 */
public class CheckRecycleAdapter extends RecyclerView.Adapter<CheckRecycleAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    private int mSelectedItem = -1;

    public interface OnItemClickListener {
        void onItemClick(String holder, int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public CheckRecycleAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_advanced_options, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cb_choose.setText(mDatas.get(position));

        holder.cb_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRangeChanged(0, mDatas.size());
                if (mListener != null) {
                    mListener.onItemClick(mDatas.get(holder.getLayoutPosition()), holder.getLayoutPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(String data, int pos) {
        mDatas.add(pos, data);
        notifyItemInserted(pos);
    }

    public void removeData(int pos) {
        mDatas.remove(pos);

        notifyDataSetChanged();
        notifyItemRemoved(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb_choose;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_choose = itemView.findViewById(R.id.cb_choose);
        }
    }
}
