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
import com.shanjing.hotattention.bean.TopicSearchBean;

import java.util.List;

/**
 * 话题列表适配器
 */
public class TopicSearchAdapter extends RecyclerView.Adapter<TopicSearchAdapter.ViewHolder> {

    private Context context;
    private List<TopicSearchBean.DataBean> listTopicSearch;

    public TopicSearchAdapter(Context context, List<TopicSearchBean.DataBean> listTopicSearch) {
        this.context = context;
        this.listTopicSearch = listTopicSearch;
    }

    @NonNull
    @Override
    public TopicSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, null, false);
        TopicSearchAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicSearchAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText("#" + listTopicSearch.get(i).getName() + "#");
        viewHolder.tv_content.setText(listTopicSearch.get(i).getIntr());
        //item的点击事件
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(listTopicSearch.get(viewHolder.getLayoutPosition()), viewHolder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopicSearch.size();
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
        void onItemClick(TopicSearchBean.DataBean holder, int pos);
    }

    private TopicSearchAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(TopicSearchAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

}
