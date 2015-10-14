package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:59
 */
@Entity(value = "age", noClassnameStored = true)
public class AgePo extends BaseBean {

    private String key;
    private String value;

    public AgePo(){}

    public AgePo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
