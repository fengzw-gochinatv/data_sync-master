package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:57
 */
@Entity(value = "category", noClassnameStored = true)
public class CategoryPo extends BaseBean {

    private Long categoryId;
    private Long cid;
    private String name;
    private int status;
    private String displayOrder;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

}
