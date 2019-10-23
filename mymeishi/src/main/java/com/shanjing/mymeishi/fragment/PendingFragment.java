package com.shanjing.mymeishi.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.bean.BaseData;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.observer.DataObserver;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.View.RoundCornerDialog;
import com.shanjing.mymeishi.adapter.ShoppingCarAdapter;
import com.shanjing.mymeishi.api.GitHubService;
import com.shanjing.mymeishi.bean.AddShopBean;
import com.shanjing.mymeishi.bean.EditShopBean;
import com.shanjing.mymeishi.bean.ShoppingCarDataBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 待付款
 */
public class PendingFragment extends BaseFragment {

    private SmartRefreshLayout mRefresh;
    private ExpandableListView elv_shopping_car;
    private ShoppingCarAdapter shoppingCarAdapter;
    private TextView tv, tv_price;
    private Button btn_sumbit;
    private LinearLayout ll_select_all;
    private ImageView iv_select_all;
    private LinearLayout ll;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_pending;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    private void initData() {
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
        mRefresh = view.findViewById(R.id.refresh);
        elv_shopping_car = view.findViewById(R.id.elv_shopping_car);
        tv = view.findViewById(R.id.tv);
        tv_price = view.findViewById(R.id.tv_price);
        btn_sumbit = view.findViewById(R.id.btn_sumbit);
        ll_select_all = view.findViewById(R.id.ll_select_all);
        iv_select_all = view.findViewById(R.id.iv_select_all);
        ll = view.findViewById(R.id.ll);
    }

    private LoadingDialog loadingDialog;

    @Override
    public void fetchData() {
        Log.d("ssss", "fragment_pending---------*********/////////");
        RxHttpUtils.createApi(GitHubService.class)
                .getShoppCar(1)
                .compose(Transformer.<BaseData<List<ShoppingCarDataBean.DataBean>>>switchSchedulers(loadingDialog))
                .subscribe(new DataObserver<List<ShoppingCarDataBean.DataBean>>() {

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtil.showShort(getActivity(), errorMsg);
                    }

                    @Override
                    protected void onSuccess(final List<ShoppingCarDataBean.DataBean> data) {
                        if (data == null || data.size() <= 0) {
                            ToastUtil.showShort(getActivity(), "购物车是空的");
                        } else {
                            shoppingCarAdapter = new ShoppingCarAdapter(getActivity(), ll_select_all,
                                    iv_select_all, data, btn_sumbit, tv_price);
                            elv_shopping_car.setAdapter(shoppingCarAdapter);
                            ll.setVisibility(View.VISIBLE);//有数据则显示页面

                            /**
                             * 长按删除
                             */
                            elv_shopping_car.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                    final long packedPosition = elv_shopping_car.getExpandableListPosition(position);
                                    final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                                    final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
                                    //长按的是group的时候，childPosition = -1
                                    if (childPosition != -1) {

                                        view = View.inflate(getActivity(), R.layout.dialog_two_btn, null);
                                        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(getActivity(), 0, 0, view, R.style.RoundCornerDialog);
                                        roundCornerDialog.show();
                                        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
                                        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

                                        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
                                        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
                                        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
                                        tv_message.setText("确定要删除商品吗？");

                                        //确定
                                        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                roundCornerDialog.dismiss();

                                                ShoppingCarDataBean.DataBean datasBean = data.get(groupPosition);
                                                ShoppingCarDataBean.DataBean.CartListBean goodsBean = datasBean.getCartList().get(childPosition);

                                                RxHttpUtils.createApi(GitHubService.class)
                                                        .deletShopoCar("" + goodsBean.getId())
                                                        .compose(Transformer.<BaseData<AddShopBean>>switchSchedulers(loadingDialog))
                                                        .subscribe(new Observer<BaseData<AddShopBean>>() {
                                                            @Override
                                                            public void onSubscribe(Disposable d) {

                                                            }

                                                            @Override
                                                            public void onNext(BaseData<AddShopBean> listBaseData) {
                                                                ToastUtil.showShort(getActivity(), "" + listBaseData.getMsg());
                                                                fetchData();
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                ToastUtil.showShort(getActivity(), "" + e);
                                                            }

                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });

                                            }
                                        });
                                        //取消
                                        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                roundCornerDialog.dismiss();
                                            }
                                        });
                                    }
                                    return false;
                                }
                            });

                            if (data != null && data.size() > 0) {
                                //使所有组展开
                                for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                                    elv_shopping_car.expandGroup(i);
                                }

                                //使组点击无效果
                                elv_shopping_car.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                                    @Override
                                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                                int groupPosition, long id) {
                                        return true;
                                    }
                                });
                            }
                        }
                    }
                });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

}
