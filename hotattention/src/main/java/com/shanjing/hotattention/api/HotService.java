package com.shanjing.hotattention.api;

import com.shanjing.hotattention.bean.AddCollectBean;
import com.shanjing.hotattention.bean.AddCommentBean;
import com.shanjing.hotattention.bean.AddLikeBean;
import com.shanjing.hotattention.bean.CollectListBean;
import com.shanjing.hotattention.bean.CommentListBean;
import com.shanjing.hotattention.bean.DelectCollectBean;
import com.shanjing.hotattention.bean.DelectLikeBean;
import com.shanjing.hotattention.bean.HeadcountBean;
import com.shanjing.hotattention.bean.HelpListBean;
import com.shanjing.hotattention.bean.HelpcountBean;
import com.shanjing.hotattention.bean.HomeBean;
import com.shanjing.hotattention.bean.HomeClassifyBean;
import com.shanjing.hotattention.bean.IsCollectBean;
import com.shanjing.hotattention.bean.IsLikeBean;
import com.shanjing.hotattention.bean.LikeListBean;
import com.shanjing.hotattention.bean.OneCommentListBean;
import com.shanjing.hotattention.bean.QuestionsAnswersListBean;
import com.shanjing.hotattention.bean.SearchListBean;
import com.shanjing.hotattention.bean.AddArticleBean;
import com.shanjing.hotattention.bean.AddHelpBean;
import com.shanjing.hotattention.bean.AddQAABean;
import com.shanjing.hotattention.bean.AddTopicBean;
import com.shanjing.hotattention.bean.TopicListBean;
import com.shanjing.hotattention.bean.TopicSearchBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HotService {
    //首页分类
    @GET("hots")
    Observable<HomeClassifyBean> getHomeClassifyData();

    //首页数据
    @GET("cmsnews")
    Observable<HomeBean> getHomeData(@Query("page") int page, @Query("category_id") String category_id);

    //添加点赞记录
    @POST("judgelikeds")
    Observable<AddLikeBean> addLike(@Body RequestBody route);

    //删除点赞记录
    @DELETE("judgelikeds/{id}")
    Observable<DelectLikeBean> deleteLike(@Path("id") String id);

    //用户点赞列表
    @GET("judgelikeds")
    Observable<LikeListBean> getLikeList(@Query("page") int page, @Query("member_id") String member_id);

    //判断是否点赞
    @GET("judgelikeds/isliked")
    Observable<IsLikeBean> isLike(@Query("member_id") String member, @Query("news_id") String news,
                                  @Query("create_by") String create);

    //添加收藏
    @POST("membercollects")
    Observable<AddCollectBean> addCollect(@Body RequestBody route);

    //删除收藏记录
    @DELETE("membercollects/{id}")
    Observable<DelectCollectBean> deleteCollect(@Path("id") String id);

    //判断是否收藏
    @GET("membercollects/iscollect")
    Observable<IsCollectBean> isCollect(@Query("member_id") String member, @Query("resouce_id") String resouce,
                                        @Query("create_by") String create);

    //用户收藏列表
    @GET("membercollects")
    Observable<CollectListBean> getCollectList(@Query("page") int page, @Query("member_id") String member_id);

    //添加评论
    @POST("judgelists")
    Observable<AddCommentBean> addComment(@Body RequestBody route);

    //用户评论列表
    @GET("judgelists")
    Observable<CommentListBean> getCommentList(@Query("page") int page, @Query("member_id") String member_id);

    //单个内容评论列表
    @GET("judgelists/fulllist")
    Observable<OneCommentListBean> getOneCommentList(@Query("page") int page, @Query("news_id") String news_id);

    //搜索
    @GET("keywordsearches")
    Observable<SearchListBean> getSearchList(@Query("page") int page, @Query("keyword") String keyword);

    //问答首页列表
    @GET("questionanswers/{id}")
    Observable<QuestionsAnswersListBean> getQuestionsAnswersList(@Path("id") String id, @Query("page") int page);

    //问答用户头像和回复的条数
    @POST("questionanswers/mlistcount")
    Observable<HeadcountBean> getHeadcount(@Body RequestBody route);

    //帮忙首页列表
    @GET("helpanswers/{id}")
    Observable<HelpListBean> getHelpList(@Path("id") String id, @Query("page") int page);

    //帮忙用户头像和回复的条数
    @POST("helpanswers/mlistcount")
    Observable<HelpcountBean> getHelpcount(@Body RequestBody route);

    //发布文章
    @POST("cmsnews")
    Observable<AddArticleBean> addArticle(@Body RequestBody route);

    //发布问答
    @POST("questionanswers")
    Observable<AddQAABean> addQAA(@Body RequestBody route);

    //发布帮忙
    @POST("helpanswers")
    Observable<AddHelpBean> addHelp(@Body RequestBody route);

    //话题列表
    @GET("cmstopics")
    Observable<TopicListBean> getTopicList();

    //话题搜索
    @GET("cmstopics/search")
    Observable<TopicSearchBean> getTopicSearchList(@Query("Keyword") String Keyword);

    //添加话题
    @POST("cmstopics")
    Observable<AddTopicBean> addTopic(@Body RequestBody route);
}
