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
 * 行业分类适配器
 */
public class ClassificationOfIndustryAdapter extends RecyclerView.Adapter<ClassificationOfIndustryAdapter.ViewHolder> {

    private Context context;
    private List<String> list;
    private LinearLayout ll_on_off;
    private ImageView iv;

    public ClassificationOfIndustryAdapter(Context context, List<String> list, LinearLayout ll_on_off, ImageView iv) {
        this.context = context;
        this.list = list;
        this.ll_on_off = ll_on_off;
        this.iv = iv;
    }

    public interface OnItemClickListener {
        void onItemClick(List<String> holder, int pos);
    }

    private ClassificationOfIndustryAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(ClassificationOfIndustryAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ClassificationOfIndustryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, null, false);
        ClassificationOfIndustryAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    boolean isOpen = false;

    @Override
    public void onBindViewHolder(@NonNull final ClassificationOfIndustryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_city_name.setText(list.get(i));
        if (i == myposition) {//设置选中item的字体颜色
            viewHolder.tv_city_name.setTextColor(context.getResources().getColor(R.color.color_pink));
            viewHolder.tv_city_name.setBackgroundResource(R.drawable.shape_tv_pink);
        } else {
            viewHolder.tv_city_name.setTextColor(context.getResources().getColor(R.color.color_text));
            viewHolder.tv_city_name.setBackgroundResource(R.drawable.shape_tv_bg_gary);
        }
        viewHolder.tv_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRangeChanged(0, list.size());
                if (mListener != null) {
                    mListener.onItemClick(Collections.singletonList(list.get(viewHolder.getLayoutPosition())), viewHolder.getLayoutPosition());
                }
            }
        });

        //行业分类的展开
        ll_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    iv.setBackgroundResource(R.drawable.icon_up);
                    isOpen = true;
                    notifyDataSetChanged();
                } else {
                    iv.setBackgroundResource(R.drawable.icon_down);
                    isOpen = false;
                    notifyDataSetChanged();
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
        if (!isOpen) {
            return 12;
        } else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_city_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city_name = itemView.findViewById(R.id.tv_city_name);
        }
    }
}
