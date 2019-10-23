package com.shanjing.hotattention.bean;

/**
 * 添加话题
 */
public class AddTopicBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : {"id":"362296635276918784","name":"新话题","sec_name":null,"type":null,"logo":null,"intr":null,"create_date":null}
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
         * id : 362296635276918784
         * name : 新话题
         * sec_name : null
         * type : null
         * logo : null
         * intr : null
         * create_date : null
         */

        private String id;
        private String name;
        private Object sec_name;
        private Object type;
        private Object logo;
        private Object intr;
        private Object create_date;

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

        public Object getSec_name() {
            return sec_name;
        }

        public void setSec_name(Object sec_name) {
            this.sec_name = sec_name;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public Object getIntr() {
            return intr;
        }

        public void setIntr(Object intr) {
            this.intr = intr;
        }

        public Object getCreate_date() {
            return create_date;
        }

        public void setCreate_date(Object create_date) {
            this.create_date = create_date;
        }
    }
}
