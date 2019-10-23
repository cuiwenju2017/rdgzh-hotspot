package com.shanjing.mymeishi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.base.commonlib.BaseActivity;
import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.ToastUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.view.LoadingDialog;
import com.shanjing.mymeishi.R;
import com.shanjing.mymeishi.adapter.AfterxqAdapter;
import com.shanjing.mymeishi.api.GitHubService;
import com.shanjing.mymeishi.model.Aftershxqbean;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Aftersaledetails extends BaseActivity {

    private String shid;
    private RelativeLayout rl_back;
    private TextView tv_title;
    private ListView afterrecylist;
    private LoadingDialog loadingDialog;
    private List<Aftershxqbean.DataBean.OrderListBean> data1;
    private List<Aftershxqbean.DataBean>mdata1;
    private AfterxqAdapter afterxqAdapter;
    private View bottomView;
    private TextView after_order_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftersaledetails);
        Intent intent = getIntent();
        shid = intent.getStringExtra("shid");
        initView();
        qingqiu();
    }



    private void initView() {
        bottomView = LayoutInflater.from(Aftersaledetails.this).inflate(R.layout.shouhouitemxx,null);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("售后详情");
        afterrecylist = (ListView)findViewById(R.id.afterrecylist);
        after_order_no = (TextView)bottomView.findViewById(R.id.after_order_no);
    }

    private void qingqiu() {
        loadingDialog = new LoadingDialog(this);
        RxHttpUtils
                .createApi(GitHubService.class)
                .aftershxq(shid)
                .compose(Transformer.<Aftershxqbean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<Aftershxqbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Aftershxqbean data) {
                        Log.d("dddwweee",data.getData().getReturnType());
                        if( data.getData().getReturnType().equals("0")){
                                 after_order_no.setText("退货退款");
                             }else if(data.getData().getReturnType().equals("1")){
                                 after_order_no.setText("换货");
                             }
                        loadingDialog.dismiss();
                        data1 = data.getData().getOrderList();
                         if(data1.size()!=0){
                             AfterxqAdapter afterxqAdapter=new AfterxqAdapter(Aftersaledetails.this,data1);
                             afterrecylist.addFooterView(bottomView);
                             afterrecylist.setAdapter(afterxqAdapter);
                         }else {
                             ToastUtils.showToast("暂无数据");
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

}
