package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.MenuPo;
import com.gochinatv.datasync.dao.mongoDao.MenuDao;
import com.gochinatv.datasync.dao.sqlDao.MenuSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-7
 * Time:上午11:13
 */
@Service
public class MenuService extends HanderDataService {
    @Resource
    MenuDao menuDao;
    @Resource
    MenuSqlDao menuSqlDao;

    public void syncData(Long menuId) {
        logger.info("MenuService  syncData menuId={}", menuId);
        try {
            List<MenuPo> list = menuSqlDao.queryList();
            List<Long> parentIds = menuSqlDao.queryParentId();
            for (long parentId : parentIds) {
                List<MenuPo> menuList = treeMenuList(list, parentId);
                for (int i = 0; i < menuList.size(); i++) {
                    MenuPo menuSQL = menuList.get(i);
                    MenuPo menuMO = menuDao.findOne("menuId", menuSQL.getMenuId());
                    if (menuMO != null) menuSQL.setId(menuMO.getId());
                    menuDao.save(menuSQL);
                }
                delMenuId(list, parentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }


    }


    //菜单树形结构
    private List<MenuPo> treeMenuList(List<MenuPo> list, Long parentId) {
        List<MenuPo> treeList = new ArrayList<MenuPo>();
        for (MenuPo menuPo : list) {
            long menuId = menuPo.getMenuId();
            long pid = menuPo.getParentId();
            if (parentId == pid) {
                List<MenuPo> c_node = treeMenuList(list, menuId);
                if (c_node != null && c_node.size() > 0) {
                    menuPo.setChildNode(c_node);
                    menuPo.setParentNode(true);
                } else {
                    menuPo.setParentNode(false);
                }
                treeList.add(menuPo);
            }
        }
        return treeList;
    }

    public void delMenuId(List<MenuPo> list, long parentId) {

        List menuIds = new ArrayList();
        List menuMoIds = new ArrayList();
        for (MenuPo menuPo : list) menuIds.add(menuPo.getMenuId());
        DBCursor cursor = menuDao.getCollection().find(new BasicDBObject("parentId", parentId));
        for (DBObject object : cursor) menuMoIds.add(object.get("menuId"));
        menuMoIds.removeAll(menuIds);
        if (!menuMoIds.isEmpty()) {
            logger.info("delMenuId  menuMoIds={}", menuMoIds);
            menuDao.deleteByQuery(menuDao.createQuery().field("menuId").in(menuMoIds));
        }
    }
}
