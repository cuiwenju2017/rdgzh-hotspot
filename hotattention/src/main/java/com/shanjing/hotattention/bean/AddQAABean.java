package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 发布问答
 */
public class AddQAABean {

    /**
     * status : success
     * code : 200
     * msg : 创建问答主题成功
     * data : [{"id":"362922820239360000","category_id":"15767952761","member_id":"15767952761","mediaservice_id":"","topic_id":"362299117726072832","title":null,"source_from":"app","display_date":null,"list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","summary":null,"Tag":null,"judge_count":null,"create_by":"15767952761","create_date":null}]
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
         * id : 362922820239360000
         * category_id : 15767952761
         * member_id : 15767952761
         * mediaservice_id :
         * topic_id : 362299117726072832
         * title : null
         * source_from : app
         * display_date : null
         * list_photo : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg
         * media_url : http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
         * summary : null
         * Tag : null
         * judge_count : null
         * create_by : 15767952761
         * create_date : null
         */

        private String id;
        private String category_id;
        private String member_id;
        private String mediaservice_id;
        private String topic_id;
        private Object title;
        private String source_from;
        private Object display_date;
        private String list_photo;
        private String media_url;
        private Object summary;
        private Object Tag;
        private Object judge_count;
        private String create_by;
        private Object create_date;

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

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
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

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getTag() {
            return Tag;
        }

        public void setTag(Object Tag) {
            this.Tag = Tag;
        }

        public Object getJudge_count() {
            return judge_count;
        }

        public void setJudge_count(Object judge_count) {
            this.judge_count = judge_count;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public Object getCreate_date() {
            return create_date;
        }

        public void setCreate_date(Object create_date) {
            this.create_date = create_date;
        }
    }
}
