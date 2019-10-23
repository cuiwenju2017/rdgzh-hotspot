package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanjing.hotattention.R;
import com.shanjing.hotattention.bean.AddCommentBean;
import com.shanjing.hotattention.bean.HomeBean;

import org.w3c.dom.Comment;

import java.util.List;

import me.codeboy.android.aligntextview.AlignTextView;

/**
 * 评论适配器
 */
public class RecyclerCommentAdapter extends RecyclerView.Adapter<RecyclerCommentAdapter.ViewHolder> {

    private Context context;
    private List<HomeBean.DataBean.JudgeListsBean> listComment;

    public RecyclerCommentAdapter(Context context, List<HomeBean.DataBean.JudgeListsBean> listComment) {
        this.context = context;
        this.listComment = listComment;
    }

    @NonNull
    @Override
    public RecyclerCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, null, false);
        RecyclerCommentAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCommentAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_create_date.setText(listComment.get(i).getCreate_date());
        viewHolder.tv_context.setText(listComment.get(i).getContext());
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public void addComment(HomeBean.DataBean.JudgeListsBean comment){
        listComment.add(comment);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_create_date,tv_context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_create_date = itemView.findViewById(R.id.tv_create_date);
            tv_context = itemView.findViewById(R.id.tv_context);
        }
    }
}
