package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 帮忙
 */
public class HelpListBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"361808588462620672","member_id":"123123","mediaservice_id":"0","help_topic_id":"1","answerpid":"0","title":"","source_from":"123123","display_date":null,"list_photo":"","media_url":"视频","summary":"","content":"123123123123","create_by":123123,"create_date":"2019-06-23 09:38:09"},{"id":"361808260778426368","member_id":"123123","mediaservice_id":"0","help_topic_id":"1","answerpid":"0","title":"","source_from":"123123","display_date":null,"list_photo":"","media_url":"视频","summary":"","content":"123123123123","create_by":123123,"create_date":"2019-06-23 09:36:51"},{"id":"361622075376402432","member_id":"123123","mediaservice_id":"0","help_topic_id":"1","answerpid":"0","title":"","source_from":"123123","display_date":null,"list_photo":"","media_url":"视频","summary":"","content":"123123123123","create_by":123123,"create_date":"2019-06-22 21:17:00"}]
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
         * id : 361808588462620672
         * member_id : 123123
         * mediaservice_id : 0
         * help_topic_id : 1
         * answerpid : 0
         * title :
         * source_from : 123123
         * display_date : null
         * list_photo :
         * media_url : 视频
         * summary :
         * content : 123123123123
         * create_by : 123123
         * create_date : 2019-06-23 09:38:09
         */

        private String id;
        private String member_id;
        private String mediaservice_id;
        private String help_topic_id;
        private String answerpid;
        private String title;
        private String source_from;
        private Object display_date;
        private String list_photo;
        private String media_url;
        private String summary;
        private String content;
        private int create_by;
        private String create_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getHelp_topic_id() {
            return help_topic_id;
        }

        public void setHelp_topic_id(String help_topic_id) {
            this.help_topic_id = help_topic_id;
        }

        public String getAnswerpid() {
            return answerpid;
        }

        public void setAnswerpid(String answerpid) {
            this.answerpid = answerpid;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
