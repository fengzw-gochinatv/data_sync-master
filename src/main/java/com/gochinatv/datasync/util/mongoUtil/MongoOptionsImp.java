package com.gochinatv.datasync.util.mongoUtil;

import com.mongodb.MongoOptions;

/**
 * Created by shhao.
 * Date: 15-4-9
 * Time:下午5:57
 */
public class MongoOptionsImp extends MongoOptions {

    public MongoOptionsImp() {
        super();
    }

    public void setSlaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
    }
}
