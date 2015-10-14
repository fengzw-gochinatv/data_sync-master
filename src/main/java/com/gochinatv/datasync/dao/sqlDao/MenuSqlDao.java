package com.gochinatv.datasync.dao.sqlDao;

import com.gochinatv.datasync.bean.MenuPo;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:21
 */
public interface MenuSqlDao {

    public MenuPo getMenuPo(Long menuId);

    public List<MenuPo> queryList();
    public List queryParentId();
}
