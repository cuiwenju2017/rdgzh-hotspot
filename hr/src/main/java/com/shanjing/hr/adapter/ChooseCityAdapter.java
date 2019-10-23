package com.shanjing.hr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanjing.hr.R;
import com.shanjing.hr.bean.CityBean;
import java.util.List;

/**
 * 省列表适配器
 */
public class ChooseCityAdapter extends RecyclerView.Adapter<ChooseCityAdapter.ViewHolder> {

    private Context context;
    private List<CityBean.DataBean> list;

    public ChooseCityAdapter(Context context, List<CityBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(CityBean.DataBean holder, int pos);
    }

    private ChooseCityAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(ChooseCityAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ChooseCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, null, false);
        ChooseCityAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChooseCityAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_s.setText(list.get(i).getName());
        if (i == myposition) {//设置选中item的字体颜色
            viewHolder.tv_s.setTextColor(context.getResources().getColor(R.color.color_pink));
        } else {
            viewHolder.tv_s.setTextColor(context.getResources().getColor(R.color.color_text));
        }
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRangeChanged(0, list.size());
                if (mListener != null) {
                    mListener.onItemClick(list.get(viewHolder.getLayoutPosition()), viewHolder.getLayoutPosition());
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
        private TextView tv_s;
        private LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_s = itemView.findViewById(R.id.tv_s);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
