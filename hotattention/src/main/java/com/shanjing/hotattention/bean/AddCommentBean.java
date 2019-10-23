package com.shanjing.hotattention.bean;

/**
 * 添加评论
 */
public class AddCommentBean {

    /**
     * status : success
     * code : 200
     * msg : 评论成功
     * data : {"id":359021697413677056,"pid":"15767952761","news_id":"9201132896799","context":"这是我的测试评论","create_date":null,"media_url":null,"member_id":"15767952761"}
     */

    private String status;
    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 359021697413677056
         * pid : 15767952761
         * news_id : 9201132896799
         * context : 这是我的测试评论
         * create_date : null
         * media_url : null
         * member_id : 15767952761
         */

        private long id;
        private String pid;
        private String news_id;
        private String context;
        private Object create_date;
        private Object media_url;
        private String member_id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        public Object getCreate_date() {
            return create_date;
        }

        public void setCreate_date(Object create_date) {
            this.create_date = create_date;
        }

        public Object getMedia_url() {
            return media_url;
        }

        public void setMedia_url(Object media_url) {
            this.media_url = media_url;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }
}
