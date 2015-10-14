package com.gochinatv.datasync.bean;

import com.alibaba.fastjson.JSONObject;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * Created by shhao.
 * Date: 14-11-7
 * Time:下午3:29
 */
public class BaseBean implements Serializable {
    @Id
    protected String id = new ObjectId().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
