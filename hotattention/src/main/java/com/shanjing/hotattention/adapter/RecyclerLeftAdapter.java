package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.bean.HomeClassifyBean;

import java.util.List;

/**
 * 首页分类适配器
 */
public class RecyclerLeftAdapter extends RecyclerView.Adapter<RecyclerLeftAdapter.ViewHolder> {

    private Context context;
    private List<HomeClassifyBean.DataBean> dataBeans;

    public interface OnItemClickListener {
        void onItemClick(HomeClassifyBean.DataBean holder, int pos);
    }

    private RecyclerLeftAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(RecyclerLeftAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public RecyclerLeftAdapter(Context context, List<HomeClassifyBean.DataBean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public RecyclerLeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_hot_list_item, null, false);
        RecyclerLeftAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private int mSelectedItem = -1;

    @Override
    public void onBindViewHolder(@NonNull final RecyclerLeftAdapter.ViewHolder viewHolder, int i) {
        viewHolder.rb_category_name.setText(dataBeans.get(i).getCategory_name());
        viewHolder.rb_category_name.setChecked(i == mSelectedItem);
        viewHolder.rb_category_name.setTag(i);

        viewHolder.rb_category_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = (int) v.getTag();
                notifyItemRangeChanged(0, dataBeans.size());
                if (mListener != null) {
                    mListener.onItemClick(dataBeans.get(viewHolder.getLayoutPosition()), viewHolder.getLayoutPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton rb_category_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb_category_name = itemView.findViewById(R.id.rb_category_name);
        }

    }
}
