package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-17
 * Time:下午6:31
 */
@Entity(value = "live_type",noClassnameStored = true)
public class TvLive{

    private String liveNum; //节目号
    private String liveName;//频道名
    private String liveTypeId;//频道类型  央视、卫视、轮播。。。
    private String liveSource;//来源    YouTube、原力。。。
    private String sourceAddress;//源地址
    private String imgUrl;//图标url
    private String status;//是否生效 0失效 1生效

    public String getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(String liveNum) {
        this.liveNum = liveNum;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(String liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    public String getLiveSource() {
        return liveSource;
    }

    public void setLiveSource(String liveSource) {
        this.liveSource = liveSource;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
