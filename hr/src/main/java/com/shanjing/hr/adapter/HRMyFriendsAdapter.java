package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.List;

/**
 * 我的好友适配器
 */
public class HRMyFriendsAdapter extends RecyclerView.Adapter<HRMyFriendsAdapter.ViewHolder> {

    private Context context;
    private List<String> list;

    public HRMyFriendsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HRMyFriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_friends, null, false);
        HRMyFriendsAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HRMyFriendsAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_name.setText(list.get(i));

        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewHolder.isCheck) {
                    viewHolder.iv_check.setBackgroundResource(R.drawable.checkbox_hr_on);
                    viewHolder.isCheck = true;
                } else {
                    viewHolder.iv_check.setBackgroundResource(R.drawable.checkbox_hr_off);
                    viewHolder.isCheck = false;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private RelativeLayout rl;
        private ImageView iv_check;
        private boolean isCheck = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            rl = itemView.findViewById(R.id.rl);
            iv_check = itemView.findViewById(R.id.iv_check);
        }
    }
}
