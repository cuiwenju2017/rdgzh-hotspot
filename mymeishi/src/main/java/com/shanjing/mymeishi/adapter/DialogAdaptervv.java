package com.shanjing.mymeishi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.model.Xqwupwc;

import java.util.List;

public class DialogAdaptervv extends BaseAdapter {
    private Context context;
    private List<Xqwupwc.DataBean.OrderListBean> list;
    private List<Xqwupwc.DataBean> mmlist;


    public DialogAdaptervv(Context context, List<Xqwupwc.DataBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }
    private RefunAdapter.buttonViewClickListener buttonViewClickListener;
    public void setbuoontItemClickListener(RefunAdapter.buttonViewClickListener listener) {
        buttonViewClickListener = listener;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final MyViewHolder holder;
        if(convertView == null) {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_tupianxze, viewGroup, false);
            holder.infor =(ImageView) convertView.findViewById(R.id.dialog_tupxz_iv_fm);
            holder.title =(TextView) convertView.findViewById(R.id.tupxzdiialog_title);
            holder.num =(TextView) convertView.findViewById(R.id.tupxzdialog_tv_num);
            holder.tu_iv_select =(CheckBox) convertView.findViewById(R.id.tu_iv_select);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        final Xqwupwc.DataBean.OrderListBean dataBean=list.get(position);   
        Glide.with(context).load(dataBean.getIcon()).into(holder.infor);
        holder.title.setText(dataBean.getGoodsTitle());
        String numm=Integer.toString(dataBean.getNum());
        holder.num.setText(numm);
//        holder.tu_iv_select.setChecked(dataBean.ischeck);
        holder.tu_iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <list.size() ; i++) {

                    if (i == position){
                        list.get(i).ischeck = true;
                        Log.d("dddsww",i+"++++"+position);
                    }else {

                    }
                    //单选框
//                    else {
//                        list.get(i).ischeck = false;
//                    }
                }
                 buttonViewClickListener.onbuttonItemClickListener(position,list);
                 notifyDataSetChanged();


            }

        });
        return convertView;
    }
    public static class MyViewHolder {
        private ImageView infor;
        private TextView title;
        private TextView num;
        private CheckBox tu_iv_select;
    }


}
