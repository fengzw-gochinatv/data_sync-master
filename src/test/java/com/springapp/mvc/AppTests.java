package com.springapp.mvc;

import com.alibaba.druid.util.HttpClientUtils;
import com.gochinatv.datasync.bean.AlbumPo;
import com.gochinatv.datasync.bean.VideoPo;
import com.gochinatv.datasync.dao.mongoDao.VideoDao;
import com.gochinatv.datasync.dao.sqlDao.AlbumSqlDao;
import com.gochinatv.datasync.dao.sqlDao.MenuSqlDao;
import com.gochinatv.datasync.dao.sqlDao.VideoSqlDao;
import com.gochinatv.datasync.service.*;
import com.gochinatv.datasync.service.SyncData.ServiceDataCheck;
import com.gochinatv.datasync.util.HttpClientTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;
import java.awt.geom.Area;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:dispatcher-servlet.xml")
public class AppTests {
    private MockMvc mockMvc;
    @Resource
    MenuService menuService;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    @Resource
    VideoDao videoDao;
    @Resource
    VideoSqlDao videoSqlDao;
    @Resource
    AlbumSqlDao albumSqlDao;
    @Resource
    MenuSqlDao menuSqlDao;
    @Resource
    LabelAlbumService labelAlbumService;
    @Resource
    FocusService focusService;
    @Resource
    ChannelService channelService;
    @Resource
    CategoryService categoryService;
    @Resource
    AreaService areaService;
    @Resource
    AlbumService albumService;
    @Resource
    AgeService ageService;
    @Resource
    VideoService videoService;
    @Resource
    ServiceDataCheck serviceDataCheck;

    @Resource
    CarouseService carouseService;
    @Resource
    LiveService liveService;
    @Test
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }

    @Test
    public void getVideo() {
        videoService.syncData(346225l);
    }

    @Test
    public void saveAlbum() {

    }



    @Test
        public void getMenuTree() {
       // menuService.syncData(-1l);
        liveService.syncData(-1l);
    }

    @Test
    public void getLabelAlbum() {

      labelAlbumService.syncData(14l);
//        labelAlbumService.resetLabelAlbum();
  //      serviceDataCheck.labelAlbumCheck(null);
    //    albumService.syncData(46686l);
    }

    @Test
    public void getFocus() {
       /* List list = menuService.treeMenuList(menuSqlDao.queryList(), 0l);
        System.out.println(JSONObject.toJSONString(list));*/
       // focusService.syncData(1l);
        focusService.resetFocus();
    }
    @Test
    public void channel() {
       /* List list = menuService.treeMenuList(menuSqlDao.queryList(), 0l);
        System.out.println(JSONObject.toJSONString(list));*/
       // channelService.syncData(200l);
        channelService.resetChannel();
    }
    @Test
    public void category() {

        categoryService.resetCategory();
    }
    @Test
    public void area() {
       /* List list = menuService.treeMenuList(menuSqlDao.queryList(), 0l);
        System.out.println(JSONObject.toJSONString(list));*/
        //areaService.syncData(7l);
        areaService.resetArea();
    }


    @Test
    public void age(){
        ageService.syncData();
    }


    @Test
      public void check(){
       // serviceDataCheck.albumCheck();
        serviceDataCheck.videoCheck();
    }

    @Test
    public void carouse(){
        carouseService.resetCarouse();
    }
}
