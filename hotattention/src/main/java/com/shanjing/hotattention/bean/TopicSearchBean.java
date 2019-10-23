package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 话题搜索
 */
public class TopicSearchBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"360743473663442944","name":"话题发射点发射点","sec_name":"话题副标题","type":0,"logo":"logo地址","intr":"话题简介","create_date":"2019-06-20 11:05:45"},{"id":"360743759975022592","name":"话题名字（唯一vc项）","sec_name":"话题副标题","type":0,"logo":"logo地址","intr":"话题简介","create_date":"2019-06-20 11:06:54"},{"id":"360744341200699392","name":"话题vc必填项）","sec_name":"话题副标题","type":0,"logo":"logo地址","intr":"话题简介","create_date":"2019-06-20 11:09:12"},{"id":"360811906874736640","name":"话题名字（唯一必填项）","sec_name":"话题副标题","type":0,"logo":"logo地址","intr":"话题简介","create_date":"2019-06-20 15:37:42"}]
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
         * id : 360743473663442944
         * name : 话题发射点发射点
         * sec_name : 话题副标题
         * type : 0
         * logo : logo地址
         * intr : 话题简介
         * create_date : 2019-06-20 11:05:45
         */

        private String id;
        private String name;
        private String sec_name;
        private int type;
        private String logo;
        private String intr;
        private String create_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSec_name() {
            return sec_name;
        }

        public void setSec_name(String sec_name) {
            this.sec_name = sec_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getIntr() {
            return intr;
        }

        public void setIntr(String intr) {
            this.intr = intr;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }
    }
}
