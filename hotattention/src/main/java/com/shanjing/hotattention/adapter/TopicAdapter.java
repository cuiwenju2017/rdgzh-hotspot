package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.bean.TopicListBean;
import java.util.List;
/**
 * 话题列表适配器
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context context;
    private List<TopicListBean.DataBean> list;

    public TopicAdapter(Context context, List<TopicListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, null, false);
        TopicAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText("#" + list.get(i).getName() + "#");
        viewHolder.tv_content.setText(list.get(i).getIntr());
        //item的点击事件
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(list.get(viewHolder.getLayoutPosition()), viewHolder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_content;
        private LinearLayout ll;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TopicListBean.DataBean holder, int pos);
    }

    private TopicAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(TopicAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

}
