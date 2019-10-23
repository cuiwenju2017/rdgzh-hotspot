package com.shanjing.hotattention.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commonlib.RxHttpUtils;
import com.base.commonlib.interceptor.Transformer;
import com.base.commonlib.utils.ToastUtil;
import com.base.commonlib.view.LoadingDialog;
import com.google.gson.Gson;
import com.shanjing.hotattention.R;
import com.shanjing.hotattention.api.HotService;
import com.shanjing.hotattention.bean.HeadcountBean;
import com.shanjing.hotattention.bean.QuestionsAnswersListBean;
import com.shanjing.hotattention.utils.AddFriendPopWindow;
import com.shanjing.hotattention.utils.HotHomePopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 问答首页列表适配器
 */
public class QuestionsAnswersAdapter extends RecyclerView.Adapter<QuestionsAnswersAdapter.ViewHolder> {

    private Context context;
    private List<QuestionsAnswersListBean.DataBean> list;
    private GalleryAdapter galleryAdapter;

    public QuestionsAnswersAdapter(Context context, List<QuestionsAnswersListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionsAnswersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_questions_answers, null, false);
        QuestionsAnswersAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private LoadingDialog loadingDialog;
    private List<QuestionsAnswersListBean.DataBean> list2 = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    public void onBindViewHolder(@NonNull final QuestionsAnswersAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_content.setText(list.get(i).getSummary());
        //头像和回答条数适配器
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("help_topic_id", list.get(i).getId());//问答ID
        String info = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), info);
        RxHttpUtils
                .createApi(HotService.class)
                .getHeadcount(body)
                .compose(Transformer.<HeadcountBean>switchSchedulers(loadingDialog))
                .subscribe(new Observer<HeadcountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HeadcountBean headcountBean) {
                        viewHolder.tv_count.setText(headcountBean.getData().get(0).getCount() + "人回答了问题");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(context, "" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        /**
         * 视屏适配器
         */
        String str = list.get(i).getMedia_url().replace(" ", "");//去掉所有空格
        List<String> list2 = new ArrayList<>(Arrays.asList((str.split("[|]"))));

        String str2 = list.get(i).getList_photo().replace(" ", "");//去掉所有空格
        List<String> list_photo = new ArrayList<>(Arrays.asList((str2.split("[|]"))));
        //设置视频布局管理器
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//列数
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//布局纵向
        viewHolder.rv.setLayoutManager(linearLayoutManager);
        //设置适配器
        galleryAdapter = new GalleryAdapter(context, list2, list_photo);
        viewHolder.rv.setAdapter(galleryAdapter);

        //加好友弹窗
        viewHolder.iv_add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddFriendPopWindow(context).showAtBottom(viewHolder.iv_add_friend);
            }
        });

        //iv_pop弹窗
        viewHolder.iv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HotHomePopWindow hotHomePopWindow = new HotHomePopWindow(context, list.get(i).getMember_id(), list.get(i).getId(),
                        list.get(i).getMember_id());
                hotHomePopWindow.showAtBottom(viewHolder.iv_pop);
                hotHomePopWindow.iv_write.setVisibility(View.GONE);//隐藏评论
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_content, tv_count;
        private RecyclerView rv;
        private ImageView iv_add_friend, iv_pop;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            rv = itemView.findViewById(R.id.rv);
            iv_add_friend = itemView.findViewById(R.id.iv_add_friend);
            iv_pop = itemView.findViewById(R.id.iv_pop);
            tv_count = itemView.findViewById(R.id.tv_count);
        }
    }
}
