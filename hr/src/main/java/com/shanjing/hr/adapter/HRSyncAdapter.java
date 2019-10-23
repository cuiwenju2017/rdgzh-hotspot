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
 * 同步到其他平台的适配器
 */
public class HRSyncAdapter extends RecyclerView.Adapter<HRSyncAdapter.ViewHolder> {

    private Context context;
    private List<String> list;

    public HRSyncAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HRSyncAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sync, null, false);
        HRSyncAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HRSyncAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_name.setText(list.get(i));

        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewHolder.isSelect) {
                    viewHolder.iv_choose.setBackgroundResource(R.drawable.check_hr_on_synchronize);
                    viewHolder.isSelect = true;
                } else if (viewHolder.isSelect) {
                    viewHolder.iv_choose.setBackgroundResource(R.drawable.check_hr_off_synchronize);
                    viewHolder.isSelect = false;
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private boolean isSelect = false;
        TextView tv_name;
        ImageView iv_choose;
        private RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_choose = itemView.findViewById(R.id.iv_choose);
            rl = itemView.findViewById(R.id.rl);
        }
    }
}
