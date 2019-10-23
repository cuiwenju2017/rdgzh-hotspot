package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 帮忙用户头像和回复的条数
 */
public class HelpcountBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"count":"0","member":[]}]
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
         * count : 0
         * member : []
         */

        private String count;
        private List<?> member;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<?> getMember() {
            return member;
        }

        public void setMember(List<?> member) {
            this.member = member;
        }
    }
}
