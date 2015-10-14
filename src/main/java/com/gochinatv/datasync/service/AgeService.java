package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.AgePo;
import com.gochinatv.datasync.dao.mongoDao.AgeDao;
import com.gochinatv.datasync.dao.sqlDao.AgeSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class AgeService {

    @Resource
    AgeDao ageDao;

   
    public void syncData() {
        List<AgePo> list = new ArrayList();
        list.add(new AgePo("2015", "2015"));
        list.add(new AgePo("2014", "2014"));
        list.add(new AgePo("2013", "2013"));
        list.add(new AgePo("2012", "2012"));
        list.add(new AgePo("2011", "2011"));
        list.add(new AgePo("2010", "2010"));
        list.add(new AgePo("2009", "2009"));
        list.add(new AgePo("2008", "2008"));
        list.add(new AgePo("2007", "2007"));
        list.add(new AgePo("2006", "2006"));
        list.add(new AgePo("2005", "2005"));
        list.add(new AgePo("2004", "2004"));
        list.add(new AgePo("2003", "2003"));
        list.add(new AgePo("2002", "2002"));
        list.add(new AgePo("2001", "2001"));
        list.add(new AgePo("2000", "2000"));
        list.add(new AgePo("1990", "90年代"));
        list.add(new AgePo("1980", "80年代"));
        list.add(new AgePo("1970", "更早"));
        for (AgePo agePo : list) {
            AgePo a = ageDao.findOne("key", agePo.getKey());
            if (a != null) agePo.setId(a.getId());
            ageDao.save(agePo);
        }
    }


}
