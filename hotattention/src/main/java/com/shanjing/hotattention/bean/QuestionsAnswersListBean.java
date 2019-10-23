package com.shanjing.hotattention.bean;

import java.util.List;

public class QuestionsAnswersListBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"3","category_id":"0","member_id":"0","mediaservice_id":"0","topic_id":"0","title":"问答模块别人的PHP","source_from":"","display_date":null,"list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","summary":"","Tag":"","judge_count":0,"create_by":0,"create_date":"2019-06-13 16:48:58"},{"id":"2","category_id":"123123123","member_id":"0","mediaservice_id":"0","topic_id":"1","title":"问答模块第二个标题","source_from":"","display_date":null,"list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","summary":"","Tag":"","judge_count":0,"create_by":0,"create_date":"2019-06-13 15:09:10"},{"id":"1","category_id":"6670508647139","member_id":"0","mediaservice_id":"0","topic_id":"1","title":"问答模块第一个标题","source_from":"","display_date":null,"list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","summary":"","Tag":"","judge_count":0,"create_by":0,"create_date":"2019-06-05 11:13:44"}]
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
         * id : 3
         * category_id : 0
         * member_id : 0
         * mediaservice_id : 0
         * topic_id : 0
         * title : 问答模块别人的PHP
         * source_from :
         * display_date : null
         * list_photo : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg
         * media_url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg
         * summary :
         * Tag :
         * judge_count : 0
         * create_by : 0
         * create_date : 2019-06-13 16:48:58
         */

        private String id;
        private String category_id;
        private String member_id;
        private String mediaservice_id;
        private String topic_id;
        private String title;
        private String source_from;
        private Object display_date;
        private String list_photo;
        private String media_url;
        private String summary;
        private String Tag;
        private int judge_count;
        private int create_by;
        private String create_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMediaservice_id() {
            return mediaservice_id;
        }

        public void setMediaservice_id(String mediaservice_id) {
            this.mediaservice_id = mediaservice_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource_from() {
            return source_from;
        }

        public void setSource_from(String source_from) {
            this.source_from = source_from;
        }

        public Object getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(Object display_date) {
            this.display_date = display_date;
        }

        public String getList_photo() {
            return list_photo;
        }

        public void setList_photo(String list_photo) {
            this.list_photo = list_photo;
        }

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTag() {
            return Tag;
        }

        public void setTag(String Tag) {
            this.Tag = Tag;
        }

        public int getJudge_count() {
            return judge_count;
        }

        public void setJudge_count(int judge_count) {
            this.judge_count = judge_count;
        }

        public int getCreate_by() {
            return create_by;
        }

        public void setCreate_by(int create_by) {
            this.create_by = create_by;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }
}
