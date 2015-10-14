package com.gochinatv.datasync.dao.mongoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryImpl;

import java.util.Map;

/**
 * User: shhao
 * Date: 12-2-7
 * Time: 上午11:32
 */
public class BaseDao<T, K> extends BasicDAO<T, K> {


    public BaseDao(Class<T> entityClass, Mongo mongo, Morphia morphia, String dbName) {
        super(entityClass, mongo, morphia, dbName);
    }



    public BaseDao(Class<T> entityClass, Datastore ds) {
        super(entityClass, ds);
    }



    protected BaseDao(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
    }



    protected BaseDao(Datastore ds) {
        super(ds);
    }



    public Query<T> createQueryNative(Map json) {
        return new QueryImpl<T>(entityClazz, getCollection(), (DatastoreImpl) getDatastore(), new BasicDBObject(json));
    }





}
