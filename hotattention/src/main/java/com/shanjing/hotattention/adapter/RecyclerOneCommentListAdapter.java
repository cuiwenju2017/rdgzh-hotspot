package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.bean.OneCommentListBean;
import java.util.List;

/**
 * 单个评论列表适配器
 */
public class RecyclerOneCommentListAdapter extends RecyclerView.Adapter<RecyclerOneCommentListAdapter.ViewHolder> {

    private Context context;
    private List<OneCommentListBean.DataBean> list;

    public RecyclerOneCommentListAdapter(Context context, List<OneCommentListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerOneCommentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, null, false);
        RecyclerOneCommentListAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerOneCommentListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_create_date.setText(list.get(i).getCreate_date());
        viewHolder.tv_context.setText(list.get(i).getContext());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_create_date, tv_context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_create_date = itemView.findViewById(R.id.tv_create_date);
            tv_context = itemView.findViewById(R.id.tv_context);
        }
    }

}
