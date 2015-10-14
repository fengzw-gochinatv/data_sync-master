package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:58
 */
@Entity(value = "area", noClassnameStored = true)
public class AreaPo extends BaseBean {
    private Long areaId;
    private String name;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
