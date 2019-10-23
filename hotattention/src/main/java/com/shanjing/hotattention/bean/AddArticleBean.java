package com.shanjing.hotattention.bean;

import java.util.List;

/**
 * 创建文章
 */

public class AddArticleBean {

    /**
     * id : 362327403856396288
     * category_id : 6670508647131
     * member_id : 15767952761
     * mediaservice_id :
     * topic_id : 362296635276918784
     * title : 创建新文章
     * list_photo : http://pic.58pic.com/58pic/13/82/74/92q58PICeSI_1024.jpg
     * media_url : http://vjs.zencdn.net/v/oceans.mp4
     * summary : null
     * content : 海洋世界了解一下下！
     * default_pv : null
     * pv : null
     * judge_count : null
     * remarks : null
     * topic : 新话题
     * judgeLists : []
     */

    private String id;
    private String category_id;
    private String member_id;
    private String mediaservice_id;
    private String topic_id;
    private String title;
    private String list_photo;
    private String media_url;
    private Object summary;
    private String content;
    private Object default_pv;
    private Object pv;
    private Object judge_count;
    private Object remarks;
    private String topic;
    private List<?> judgeLists;

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

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getDefault_pv() {
        return default_pv;
    }

    public void setDefault_pv(Object default_pv) {
        this.default_pv = default_pv;
    }

    public Object getPv() {
        return pv;
    }

    public void setPv(Object pv) {
        this.pv = pv;
    }

    public Object getJudge_count() {
        return judge_count;
    }

    public void setJudge_count(Object judge_count) {
        this.judge_count = judge_count;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<?> getJudgeLists() {
        return judgeLists;
    }

    public void setJudgeLists(List<?> judgeLists) {
        this.judgeLists = judgeLists;
    }
}
