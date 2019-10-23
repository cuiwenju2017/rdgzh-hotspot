package com.shanjing.mymeishi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.activity.OrderCenterOrderMessage;
import com.shanjing.mymeishi.api.GitHubService;
import com.shanjing.mymeishi.bean.Wholebeandd;
import com.shanjing.mymeishi.model.Putscbean;
import com.shanjing.mymeishi.model.Qxdingdanbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class OrdercenterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Wholebeandd.ListBean>list;

    private Context mContext;
    private Wholebeandd.ListBean listbean;

    private TextView txt_aSpeak;
    private LoadingDialog loadingDialog;

    public void setList(ArrayList<Wholebeandd.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public OrdercenterAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public ArrayList<Wholebeandd.ListBean> getList() {
        return list;
    }
    private void startOrderMessageActivity(String orderId) {
        Intent intent = new Intent(mContext, OrderCenterOrderMessage.class);
        intent.putExtra("orderID", orderId);
        mContext.startActivity(intent);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType){
                case 0:
                    return new ViewHolder0(LayoutInflater.from(mContext).inflate(R.layout.item_list_animal, null, false));
            }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
                    final Wholebeandd.ListBean listBean=list.get(position);
                    this .listbean=listBean;

        if (viewHolder instanceof ViewHolder0) {
            Glide.with(mContext).load(listBean.getShopLogo()).into(((ViewHolder0) viewHolder).img_icon);
            ((ViewHolder0) viewHolder).txt_aName.setText(listBean.getShopName());
            txt_aSpeak = ((ViewHolder0) viewHolder).txt_aSpeak;
            if(listBean.getOrderStatus().equals("-1")){
                txt_aSpeak.setText("已取消");
            }
           else if(listBean.getOrderStatus().equals("1")){
                 txt_aSpeak.setText("待付款");
            }else if(listBean.getOrderStatus().equals("2")){
                txt_aSpeak.setText("待发货");
            }else if(listBean.getOrderStatus().equals("3")){
                txt_aSpeak.setText("待收货");
            }else if(listBean.getOrderStatus().equals("4")){
                txt_aSpeak.setText("已完成");
            }else if(listBean.getOrderStatus().equals("5")){
                txt_aSpeak.setText("已评论");
            }
            ((ViewHolder0) viewHolder).txt_aName.setText(listBean.getShopName());
            ((ViewHolder0) viewHolder).quxiaoorderid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.size()>0){
                        onDel( listBean.getOrderId());


                    }

                }


            });
        }
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class ViewHolder0 extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView txt_aName;
        private TextView txt_aSpeak;
        private CardView cardView;
        private LinearLayout quxiaoorderid;

        public ViewHolder0(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View convertView) {
            img_icon = (ImageView)convertView.findViewById(R.id.img_icon);
            txt_aName = (TextView) convertView.findViewById(R.id.txt_aName);
            txt_aSpeak = (TextView) convertView.findViewById(R.id.txt_aSpeak);
            cardView = (CardView)convertView.findViewById(R.id.cardView);
            quxiaoorderid =(LinearLayout) convertView.findViewById(R.id.quxiaoorderid);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Log.d("ddssad",list.get(getAdapterPosition()).getOrderId());

//                    startOrderMessageActivity(re.getOrderList().get(getAdapterPosition()).getOrderId(),re.getOrderList().get(getAdapterPosition()).getGoodsId(),re.getOrderList().get(getAdapterPosition()).getProduceId(),re.getOrderList().get(getAdapterPosition()).getNum(),re.getOrderList().get(getAdapterPosition()).getRenums(),re.getOrderList().get(getAdapterPosition()).getIcon(),re.getOrderList().get(getAdapterPosition()).getGoodsTitle(),re.getOrderList().get(getAdapterPosition()).getProduceName(),re.getOrderList().get(getAdapterPosition()).getPriceSrc(),re.getOrderList().get(getAdapterPosition()).getDiscountBuy(),re.getOrderList().get(getAdapterPosition()).getPriceBuy(),re.getOrderList().get(getAdapterPosition()).getId());
                    startOrderMessageActivity(list.get(getAdapterPosition()).getOrderId());


                }


            });
        }
    }
    private void onDel(String orderId) {
        loadingDialog = new LoadingDialog(mContext);
        RxHttpUtils.createApi(GitHubService.class)
                .quxiaodd(orderId)
                .compose(Transformer.<BaseData<Qxdingdanbean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Qxdingdanbean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Qxdingdanbean data) {
                        Log.d("ddwww",data.getIsOK()+"");
                        loadingDialog.dismiss();
                        ToastUtils.showToast("订单取消成功");
                        UpdataMessage.updata(true);
                        notifyDataSetChanged();


                    }
                });

    }
    private UpdataMessage UpdataMessage;

    public interface UpdataMessage{
        void updata(boolean message);
    }
    public void updatadingd(UpdataMessage UpdataMessage){
        this.UpdataMessage=UpdataMessage;
    }
}
