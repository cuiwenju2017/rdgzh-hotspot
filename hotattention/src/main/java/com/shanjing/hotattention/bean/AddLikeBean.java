package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 添加点赞
 */
public class AddLikeBean {

    /**
     * status : success
     * code : 200
     * msg : 点赞成功
     * data : [{"news_id":"7105387499948","member_id":"15767952767","id":"361094307869884416","cms_title":"Yii 最适合做什么？","cms_list_photo":"视频封面图地址：www.jjk.com/a/video","cms_mediaurl":"www.jjk.com/a/video","cms_summary":"这里是简介内容","cms_content":"发发发的零端口开放的疯狂拉到"}]
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
         * news_id : 7105387499948
         * member_id : 15767952767
         * id : 361094307869884416
         * cms_title : Yii 最适合做什么？
         * cms_list_photo : 视频封面图地址：www.jjk.com/a/video
         * cms_mediaurl : www.jjk.com/a/video
         * cms_summary : 这里是简介内容
         * cms_content : 发发发的零端口开放的疯狂拉到
         */

        private String news_id;
        private String member_id;
        private String id;
        private String cms_title;
        private String cms_list_photo;
        private String cms_mediaurl;
        private String cms_summary;
        private String cms_content;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCms_title() {
            return cms_title;
        }

        public void setCms_title(String cms_title) {
            this.cms_title = cms_title;
        }

        public String getCms_list_photo() {
            return cms_list_photo;
        }

        public void setCms_list_photo(String cms_list_photo) {
            this.cms_list_photo = cms_list_photo;
        }

        public String getCms_mediaurl() {
            return cms_mediaurl;
        }

        public void setCms_mediaurl(String cms_mediaurl) {
            this.cms_mediaurl = cms_mediaurl;
        }

        public String getCms_summary() {
            return cms_summary;
        }

        public void setCms_summary(String cms_summary) {
            this.cms_summary = cms_summary;
        }

        public String getCms_content() {
            return cms_content;
        }

        public void setCms_content(String cms_content) {
            this.cms_content = cms_content;
        }
    }
}
