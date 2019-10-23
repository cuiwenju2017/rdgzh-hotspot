package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 搜索
 */
public class SearchListBean {

    /**
     * status : success
     * code : 200
     * msg : 请求成功
     * data : [{"id":"7795221000241","category_id":"6670508647131","member_id":"15767952761","mediaservice_id":"6670508247131","topic_id":"6670588647121","title":"PHP为什么是最好的语言","list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4|http://vjs.zencdn.net/v/oceans.mp4","summary":"我认为php是最好的语言","type":"cmsnews"},{"id":"9201132896799","category_id":"6670508647131","member_id":"15767952761","mediaservice_id":"6670508247131","topic_id":"6670588647121","title":"PHP为什么是最好的语言","list_photo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg","media_url":"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4|http://vjs.zencdn.net/v/oceans.mp4|https://media.w3.org/2010/05/sintel/trailer.mp4|http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4","summary":"我认为php是最好的语言","type":"cmsnews"},{"id":"3","category_id":"0","member_id":"0","mediaservice_id":"0","topic_id":"0","title":"别人的PHP","summary":"","media_url":"视频","type":"topic"}]
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
         * id : 7795221000241
         * category_id : 6670508647131
         * member_id : 15767952761
         * mediaservice_id : 6670508247131
         * topic_id : 6670588647121
         * title : PHP为什么是最好的语言
         * list_photo : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg|https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1662232266,4128046122&fm=27&gp=0.jpg
         * media_url : http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4|http://vjs.zencdn.net/v/oceans.mp4
         * summary : 我认为php是最好的语言
         * type : cmsnews
         */

        private String id;
        private String category_id;
        private String member_id;
        private String mediaservice_id;
        private String topic_id;
        private String title;
        private String list_photo;
        private String media_url;
        private String summary;
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
