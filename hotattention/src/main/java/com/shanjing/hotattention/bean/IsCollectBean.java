package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 是否收藏
 */
public class IsCollectBean {

    /**
     * status : error
     * code : error
     * msg : 存在收藏记录（状态：已删除）
     * data : [{"status":"存在收藏记录（状态：已删除）"}]
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
         * status : 存在收藏记录（状态：已删除）
         */

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
