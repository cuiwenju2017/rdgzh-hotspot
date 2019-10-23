package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 首页分类
 */
public class HomeClassifyBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"6670508647131","pcid":"0","category_name":"视频","category_tag":"","category_icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg","category_banner":"","order_no":0},{"id":"6670508647132","pcid":"0","category_name":"直播","category_tag":"","category_icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg","category_banner":"","order_no":0},{"id":"6670508647133","pcid":"0","category_name":"影视","category_tag":"","category_icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg","category_banner":"","order_no":0},{"id":"6670508647134","pcid":"0","category_name":"游戏","category_tag":"","category_icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg","category_banner":"","order_no":0},{"id":"6670508647135","pcid":"0","category_name":"社会","category_tag":"","category_icon":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg","category_banner":"","order_no":0}]
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
         * id : 6670508647131
         * pcid : 0
         * category_name : 视频
         * category_tag :
         * category_icon : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559111195026&di=9729724d86c9f73850382baa752bd0fd&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1875096779%2C3100809793%26fm%3D214%26gp%3D0.jpg
         * category_banner :
         * order_no : 0
         */

        private String id;
        private String pcid;
        private String category_name;
        private String category_tag;
        private String category_icon;
        private String category_banner;
        private int order_no;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPcid() {
            return pcid;
        }

        public void setPcid(String pcid) {
            this.pcid = pcid;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCategory_tag() {
            return category_tag;
        }

        public void setCategory_tag(String category_tag) {
            this.category_tag = category_tag;
        }

        public String getCategory_icon() {
            return category_icon;
        }

        public void setCategory_icon(String category_icon) {
            this.category_icon = category_icon;
        }

        public String getCategory_banner() {
            return category_banner;
        }

        public void setCategory_banner(String category_banner) {
            this.category_banner = category_banner;
        }

        public int getOrder_no() {
            return order_no;
        }

        public void setOrder_no(int order_no) {
            this.order_no = order_no;
        }
    }
}
