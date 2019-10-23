package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanjing.hr.R;

import java.util.Collections;
import java.util.List;

/**
 * 发布权限设置适配器
 */
public class JurisdictionSettingAdapter extends RecyclerView.Adapter<JurisdictionSettingAdapter.ViewHolder> {

    private Context context;
    private List<String> list, list2, list3;

    public JurisdictionSettingAdapter(Context context, List<String> list, List<String> list2, List<String> list3) {
        this.context = context;
        this.list = list;
        this.list2 = list2;
        this.list3 = list3;
    }

    public interface OnItemClickListener {
        void onItemClick(List<String> holder, int pos);
    }

    private JurisdictionSettingAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(JurisdictionSettingAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public JurisdictionSettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jurisdiction, null, false);
        JurisdictionSettingAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final JurisdictionSettingAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_open.setText(list.get(i));
        viewHolder.tv_default.setText(list2.get(i));
        viewHolder.tv_all.setText(list3.get(i));

        if (i == myposition) {//设置选中item的字体颜色
            viewHolder.iv_isChoose.setBackgroundResource(R.drawable.radiobutton_hr_on);
        } else {
            viewHolder.iv_isChoose.setBackgroundResource(R.drawable.radiobutton_hr_off);
        }
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
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

        private TextView tv_open, tv_default, tv_all;
        private LinearLayout ll;
        private ImageView iv_isChoose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_open = itemView.findViewById(R.id.tv_open);
            tv_default = itemView.findViewById(R.id.tv_default);
            tv_all = itemView.findViewById(R.id.tv_all);
            ll = itemView.findViewById(R.id.ll);
            iv_isChoose = itemView.findViewById(R.id.iv_isChoose);
        }
    }
}
