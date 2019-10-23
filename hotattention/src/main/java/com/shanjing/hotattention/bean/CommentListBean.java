package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 我的评论列表
 */
public class CommentListBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"361109738819158016","pid":"15767952761","news_id":"9201132896799","context":"我来了","create_date":"2019-06-21 11:21:10","media_url":"","member_id":"15767952761","title":"PHP为什么是最好的语言","photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","mediaurl":"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4|http://vjs.zencdn.net/v/oceans.mp4|https://media.w3.org/2010/05/sintel/trailer.mp4|http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4","summary":"我认为php是最好的语言","content":"2018年，浙江社会消费零售品额增长9.0%，限额以上批发零售业商品零售额增长13.5%。今年1~4月，全省社会消费零售品增长8.3%，为全省经济稳增长提供了有力支撑。\r\n\r\n今天，浙江省人民政府召开全省批发零售业改造提升暨高品质步行街建设推进会。\r\n\r\n步行街汇集多种商业资源，既是满足居民消费的场所，也是现代城市的基本配置。目前全省已有省级特色商业街47条，在建和培育社区商业（邻里中心）项目169个。\r\n\r\n浙江接下来将深入推进湖滨步行街国家级试点，确保一期工程今年国庆前开街；选择基础较好的步行街，开展省级高品质步行街试点，建立后备培育项目库。\r\n\r\n省商务厅等10部门近日联合印发了《浙江省推动高品质步行街建设实施方案》，和小布一起来看看吧～"},{"id":"360158871332847616","pid":"123123123","news_id":"7102497407782","context":"水浅王八多","create_date":"2019-06-18 20:22:46","media_url":"","member_id":"15767952761","title":"Yii 最适合做什么？","photo":"视频封面图地址：www.jjk.com/a/video","mediaurl":"www.jjk.com/a/video","summary":"这里是简介内容","content":"发发发的零端口开放的疯狂拉到"}]
     */

    private String status;
    private String code;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 361109738819158016
         * pid : 15767952761
         * news_id : 9201132896799
         * context : 我来了
         * create_date : 2019-06-21 11:21:10
         * media_url :
         * member_id : 15767952761
         * title : PHP为什么是最好的语言
         * photo : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg
         * mediaurl : http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4|http://vjs.zencdn.net/v/oceans.mp4|https://media.w3.org/2010/05/sintel/trailer.mp4|http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4
         * summary : 我认为php是最好的语言
         * content : 2018年，浙江社会消费零售品额增长9.0%，限额以上批发零售业商品零售额增长13.5%。今年1~4月，全省社会消费零售品增长8.3%，为全省经济稳增长提供了有力支撑。

         今天，浙江省人民政府召开全省批发零售业改造提升暨高品质步行街建设推进会。

         步行街汇集多种商业资源，既是满足居民消费的场所，也是现代城市的基本配置。目前全省已有省级特色商业街47条，在建和培育社区商业（邻里中心）项目169个。

         浙江接下来将深入推进湖滨步行街国家级试点，确保一期工程今年国庆前开街；选择基础较好的步行街，开展省级高品质步行街试点，建立后备培育项目库。

         省商务厅等10部门近日联合印发了《浙江省推动高品质步行街建设实施方案》，和小布一起来看看吧～
         */

        private String id;
        private String pid;
        private String news_id;
        private String context;
        private String create_date;
        private String media_url;
        private String member_id;
        private String title;
        private String photo;
        private String mediaurl;
        private String summary;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getMediaurl() {
            return mediaurl;
        }

        public void setMediaurl(String mediaurl) {
            this.mediaurl = mediaurl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
