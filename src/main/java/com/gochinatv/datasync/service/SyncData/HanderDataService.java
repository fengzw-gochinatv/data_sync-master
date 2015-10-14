package com.gochinatv.datasync.service.SyncData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午5:23
 */

public abstract class HanderDataService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract void syncData(Long objectId) ;

}
