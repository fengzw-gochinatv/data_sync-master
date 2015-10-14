package com.gochinatv.datasync.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by shhao.
 * Date: 15-4-3
 * Time:下午2:33
 */
public class PlayInfo {

    private String remotevid;
    private Long siteId;
    private Long duration;
    private String sourceURL;
    private String hvStartTime;
    private String hvEndTime;

    public String getRemotevid() {
        return remotevid;
    }

    public void setRemotevid(String remotevid) {
        this.remotevid = remotevid;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getHvStartTime() {
        return hvStartTime;
    }

    public void setHvStartTime(String hvStartTime) {
        this.hvStartTime = hvStartTime;
    }

    public String getHvEndTime() {
        return hvEndTime;
    }

    public void setHvEndTime(String hvEndTime) {
        this.hvEndTime = hvEndTime;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
