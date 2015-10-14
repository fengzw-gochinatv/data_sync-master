package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

import java.util.Date;

/**
 * Created by shhao.
 * Date: 15-4-11
 * Time:下午12:02
 */
@Entity(value = "carousel", noClassnameStored = true)
public class CarouselPo extends BaseBean {
    private long carouseId;
    private long videoId;
    private String videoName;
    private long duration;
    private String pic_url;
    private long display;
    private String youtubeId;
    private Date updateTime;

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public long getDisplay() {
        return display;
    }

    public void setDisplay(long display) {
        this.display = display;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getCarouseId() {
        return carouseId;
    }

    public void setCarouseId(long carouseId) {
        this.carouseId = carouseId;
    }
}
