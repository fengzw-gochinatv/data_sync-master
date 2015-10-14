package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:56
 */
@Entity(value = "focus", noClassnameStored = true)
public class FocusPo extends BaseBean {

    private Long focusId;
    private String focusName;
    private int type;//TV,phone
    private String image;
    private String backImage;
    private int sort;
    private List<FocusData> data;// title,picUrl,desc,type,redirectUrl  可扩展

    public Long getFocusId() {
        return focusId;
    }

    public void setFocusId(Long focusId) {
        this.focusId = focusId;
    }

    public String getFocusName() {
        return focusName;
    }

    public void setFocusName(String focusName) {
        this.focusName = focusName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<FocusData> getData() {
        return data;
    }

    public void setData(List<FocusData> data) {
        this.data = data;
    }

    public String getImage() {
        return image==null?"":image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getBackImage() {
        return backImage==null?"":backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }
}
