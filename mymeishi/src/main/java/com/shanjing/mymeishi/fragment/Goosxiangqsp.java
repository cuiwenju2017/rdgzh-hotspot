package com.shanjing.mymeishi.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.api.GitHubService;
import com.shanjing.mymeishi.bean.AddShopBean;
import com.shanjing.mymeishi.model.Deletecolle;
import com.shanjing.mymeishi.model.Goosmessges;
import com.shanjing.mymeishi.model.Putscbean;
import com.shanjing.mymeishi.model.Shopcollbean;
import com.shanjing.mymeishi.model.Xqwupwc;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.base.commonlib.utils.ConfirmPopWindow.iv_collect;


@SuppressLint("ValidFragment")
public class Goosxiangqsp extends BaseFragment implements View.OnClickListener{



    private ConvenientBanner convenientBanner_detail;
    private LoadingDialog loadingDialog;
    private final String xxmid;
    private TextView goodstitle;
    private TextView zhekoujia;
    private TextView yuanjia;
    private ImageView img_colle;
    private TextView text_colle;
    private Boolean ischeck=false;
    private Button details_btn_jion_cart;
    private SmartRefreshLayout mRefresh;


    public Goosxiangqsp(String goodsid) {
        Log.d("ddsadd",goodsid);
        this.xxmid = goodsid;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goosxiangqsp;
    }
    @Override
    protected void init(View view, Bundle savedInstanceState) {
                initView(view);
                initDate();
    }

    private void initDate() {
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                fetchData();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });


    }

    private void initView(View view) {
        loadingDialog = new LoadingDialog(getActivity());
        mRefresh = view.findViewById(R.id.refresh);
        convenientBanner_detail =(ConvenientBanner) view.findViewById(R.id.convenientBanner_detail);
        goodstitle = (TextView)view.findViewById(R.id.goodstitle);
        zhekoujia = (TextView)view.findViewById(R.id.zhekoujia);
        yuanjia = (TextView)view.findViewById(R.id.yuanjia);
        img_colle =(ImageView) view.findViewById(R.id.img_colle);
        text_colle =(TextView) view.findViewById(R.id.text_colle);
        //加入购物车
        details_btn_jion_cart =(Button) view.findViewById(R.id.details_btn_jion_cart);
        img_colle.setOnClickListener(this);
        details_btn_jion_cart.setOnClickListener(this);
    }

    @Override
    public void fetchData() {
        Log.d("ddsad",xxmid);
        RxHttpUtils
                .createApi(GitHubService.class)
                .goodsmessage(xxmid)
                .compose(Transformer.<Goosmessges>switchSchedulers(loadingDialog))
                . subscribe(new Observer<Goosmessges>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Goosmessges goosmessges) {
                        loadingDialog.dismiss();
                        goodstitle.setText(goosmessges.getData().getTitle());
                        if(!goosmessges.getData().getIsSpecsUsable().equals("0")){
                            String pricesrc=Double.toString(goosmessges.getData().getProduceList().get(0).getPriceSrc());
                            yuanjia.setText(pricesrc);
                            String discountprice=Double.toString(goosmessges.getData().getProduceList().get(0).getDiscountPrice());
                            zhekoujia.setText(discountprice);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        RxHttpUtils
                .createApi(GitHubService.class)
                .dianpu(xxmid,0)
                .compose(Transformer.<BaseData<Shopcollbean>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<Shopcollbean>() {

                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(Shopcollbean data) {
                        loadingDialog.dismiss();
                        Log.d("aaaa",data.getIsFav());
                        if(data.getIsFav().equals("1")){
                            img_colle.setImageResource(R.drawable.ic_collect_active);
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.img_colle) {
            if (ischeck == false) {
                Gson gson = new Gson();
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("resouce_id", xxmid);
                paramsMap.put("type", "0");
                String info = gson.toJson(paramsMap);
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
                Log.d("dsad", info);
                RxHttpUtils.createApi(GitHubService.class)
                        .putcshoucang(body)
                        .compose(Transformer.<BaseData<Putscbean>>switchSchedulers(loadingDialog))
                        .subscribe(new DataObserver<Putscbean>() {

                            @Override
                            protected void onError(String errorMsg) {

                            }

                            @Override
                            protected void onSuccess(Putscbean data) {
                                loadingDialog.dismiss();
                                if (data.getIsAdd().equals("1")) {
                                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                                    img_colle.setImageResource(R.drawable.ic_collect_active);
                                    ischeck = true;
                                    Log.d("dddww", ischeck + "");
                                }

                            }
                        });
            } else if (ischeck == true) {

                RxHttpUtils
                        .createApi(GitHubService.class)
                        .deletesc(xxmid)
                        .compose(Transformer.<Deletecolle>switchSchedulers(loadingDialog))
                        .subscribe(new Observer<Deletecolle>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(Deletecolle deletecolle) {
                                loadingDialog.dismiss();
                                if (deletecolle.getStatus().equals("1")) {
                                    Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();

                                    ischeck = false;
                                    img_colle.setImageResource(R.drawable.ic_collect);
                                } else {
                                    Toast.makeText(getActivity(), "取消失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else if (i == R.id.details_btn_jion_cart) {
            Gson gson = new Gson();
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("shop_id", "1059396544446468096");//店铺ID
            paramsMap.put("collocation_id", "0");//来源搭配ID
            paramsMap.put("resource_type", "0");//来源类型(0、单品 1、搭配)
            paramsMap.put("goods_id", xxmid);//商频ID
            paramsMap.put("produce_id", "");//产品ID
            paramsMap.put("num", "1");//数量
            String info = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
            RxHttpUtils.createApi(GitHubService.class)
                    .getDd(body)
                    .compose(Transformer.<BaseData<AddShopBean>>switchSchedulers(loadingDialog))
                    .subscribe(new DataObserver<AddShopBean>() {

                        @Override
                        protected void onError(String errorMsg) {
                            ToastUtil.showShort(getActivity(), errorMsg);
                        }

                        @Override
                        protected void onSuccess(AddShopBean data) {
                            if ("1".equals(data.getIsAdd())) {
                                ToastUtil.showShort(getActivity(), "添加成功");
                            }
                        }
                    });
        }
    }
}
