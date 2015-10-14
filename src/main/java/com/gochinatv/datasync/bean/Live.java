package com.gochinatv.datasync.bean;


import com.google.code.morphia.annotations.Entity;

import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-17
 * Time:下午6:32
 */
@Entity(value = "live", noClassnameStored = true)
public class Live  extends BaseBean{
    private int liveId ;
    private String name;
    private int displayOrder;
    private List<TvLive> list;

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<TvLive> getList() {
        return list;
    }

    public void setList(List<TvLive> list) {
        this.list = list;
    }
}
