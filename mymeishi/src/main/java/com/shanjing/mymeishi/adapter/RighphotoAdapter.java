package com.shanjing.mymeishi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.activity.GoodsDetaillsActivity;
import com.shanjing.mymeishi.bean.HomeRightBean;


import java.util.ArrayList;

public class RighphotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    ArrayList<HomeRightBean.DataBean.ListBean> listbeanss;
    private ArrayList<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> list;
    public void setList(ArrayList<HomeRightBean.DataBean.ListBean.PgcGoodsListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public RighphotoAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.item_rv, null, false));

        }
        return null;
    }
    private void startOrderMessageActivity(String id) {
        Intent intent = new Intent(mContext, GoodsDetaillsActivity.class);
        intent.putExtra("goodsID", id);
        mContext.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final HomeRightBean.DataBean.ListBean.PgcGoodsListBean listBean=list.get(position);
        if (viewHolder instanceof ViewHolder0) {
            Glide.with(mContext).load(listBean.getSinglePhoto()).into(((ViewHolder0) viewHolder).image_iv);
            ((ViewHolder0) viewHolder).tv_goodsname.setText(listBean.getTitle());
            String price=Double.toString(listBean.getPriceSrc());
            ((ViewHolder0) viewHolder).tv_price.setText(price);
            ((ViewHolder0) viewHolder).image_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    startOrderMessageActivity(listbeanss.get(position).getId(),listbeanss.get(position).getPublisherId(),listbeanss.get(position).);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView image_iv;
        private TextView tv_goodsname;
        private TextView tv_price;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            image_iv = (ImageView)itemView.findViewById(R.id.iv);
            tv_goodsname = (TextView)itemView.findViewById(R.id.tv);
            tv_price = (TextView)itemView.findViewById(R.id.tv_price);
//            image_iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startOrderMessageActivity(list.get(getAdapterPosition()).getId());
////                    Log.d("dwwww",listbean.get)
//                }
//
//
//            });

        }
    }
}
