package com.gochinatv.datasync.service;

import com.gochinatv.datasync.bean.VideoPo;
import com.gochinatv.datasync.dao.mongoDao.VideoDao;
import com.gochinatv.datasync.dao.sqlDao.AlbumSqlDao;
import com.gochinatv.datasync.dao.sqlDao.VideoSqlDao;
import com.gochinatv.datasync.service.SyncData.HanderDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:23
 */
@Service
public class VideoService extends HanderDataService {
    @Resource
    VideoDao videoDao;
    @Resource
    VideoSqlDao videoSqlDao;
    @Resource
    AlbumSqlDao albumSqlDao;
    @Resource
    AlbumService albumService;


    public void syncData(Long videoId) {
        try {
            logger.info("syncData");
            VideoPo videoSQL = videoSqlDao.getVideoPo(videoId);
            VideoPo videoMO = videoDao.findOne("vid", videoId);
            if (videoSQL == null) {
                logger.info("videoSQL is null videoId={} ", videoId);
                if (videoMO != null && videoMO.getAid() != null) {
                    videoDao.delete(videoMO);
                    albumService.syncData(videoMO.getAid());
                }
            } else {
                logger.info("save  videoSQL into mongoDB videoId={} ,isdisplay={}", new Object[]{videoId, videoSQL.getIsdisplay()});
                if (videoMO != null) videoSQL.setId(videoMO.getId());
                videoDao.save(videoSQL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(), e);
        }
    }

    public void resetVideo(Long albumId) {
        try {
            logger.info("resetVideo========albumId={}", albumId);
            List<Long> list = new ArrayList<Long>();
            if (albumId > 0) {
                list.add(albumId);
            } else {
                list = albumSqlDao.getAlbumIds();
            }
            for (Long aid : list) {
            	logger.info("resetVideo========albumId={}", aid);
                List<VideoPo> videoList = videoSqlDao.getVideoList(aid);
                for (VideoPo videoPo : videoList) {
                    try {
                        logger.info("resetVideo   vid={}", videoPo.getVid());
                        VideoPo videoMO = videoDao.findOne("vid", videoPo.getVid());
                        if (videoMO != null) videoPo.setId(videoMO.getId());
                        videoDao.save(videoPo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Integer tsize = 10;

	private static Integer fsize = 0;

	private static Integer flag = 0;
	
    public void resetVideorsync() {
           final List<Long> list  = albumSqlDao.getAlbumIds();
            final int totalSize = list.size();
            fsize = totalSize / tsize;//每页多少个
    		if(totalSize % tsize>0){
    			tsize+=1;
    		}
    		for (flag = 0; flag < tsize; flag++) {
    			new Thread(new Runnable() {
    				public void run() {
    					int th = flag;
    					List<Long> pageList=new ArrayList<Long>();
    					if(th!=tsize-1) {
    						for (int i = th * fsize; i < th * fsize + fsize; i++) {
    							pageList.add(list.get(i));
    						}
    					}else{
    						for(int i=th*fsize;i<totalSize;i++){
    							pageList.add(list.get(i));
    						}
    					}
    		             logger.info("th* fsize=" + th * fsize + "  fize=" + fsize);
       					for (Long aid : pageList){
       		            	logger.info("resetVideo========albumId={}", aid);
       		                List<VideoPo> videoList = videoSqlDao.getVideoList(aid);
       		                for (VideoPo videoPo : videoList) {
       		                    try {
       		                        logger.info("resetVideo   vid={}", videoPo.getVid());
       		                        VideoPo videoMO = videoDao.findOne("vid", videoPo.getVid());
       		                        if (videoMO != null) videoPo.setId(videoMO.getId());
       		                        videoDao.save(videoPo);
       		                    } catch (Exception e) {
       		                        e.printStackTrace();
       		                    }
       		                }
       		            
       					}
    				}
    			}).start();
    			try {
    				Thread.sleep(1000 * 1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    }
}
