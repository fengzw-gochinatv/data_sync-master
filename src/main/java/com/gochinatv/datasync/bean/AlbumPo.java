package com.gochinatv.datasync.bean;

import com.gochinatv.datasync.util.StringUtil;
import com.google.code.morphia.annotations.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:14
 */
@Entity(value = "album", noClassnameStored = true)
public class AlbumPo extends BaseBean {

    //专辑id
    private Long aid;
    //专辑名称
    private String name;
    //所在频道
    private Long channelId;
    private String channelName;
    //别名
    private String englishname;
    //语言
    private String language;
    //地区
    private Long areaId;
    private String areaName;
    //简介
    private String description;
    //标签
    private String tag;
    //总集数
    private String episodes;
    //版权方
    private String copyrightName;
    private Integer isend;
    private Integer isdisplay;
    //导演
    private String directory;
    //主演
    private String starring;
    private String standardPic;
    private String ystandardPic;
    //类型
    private List categoryIds;
    private String categoryName;
    private Integer displayOrder;
    //主持人
    private String host;
    //上映时间
    private String showtimes;
    //是否更新
    private Integer isUpdate;
    //更新频率
    private String updateFre;
    //年代
    private String age;
    private Integer serialType;
    private String score;
    private String television;
    private String guest;
    private String destination;
    //终端
    private List terminal;
    private Date createTime;
    private Date modifyTime;

    private Long newVideoId;
    private Long oldVideoId;
    private String newVideoInstallments ;
    private String oldVideoInstallments ;
    private Long ischarge;
    private Long islive;
    private Long ageLevel;
    
    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getCopyrightName() {
        return copyrightName;
    }

    public void setCopyrightName(String copyrightName) {
        this.copyrightName = copyrightName;
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public Integer getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Integer isdisplay) {
        this.isdisplay = isdisplay;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getStandardPic() {
        return standardPic;
    }

    public void setStandardPic(String standardPic) {
        this.standardPic = standardPic;
    }

    public String getYstandardPic() {
        return ystandardPic;
    }

    public void setYstandardPic(String ystandardPic) {
        this.ystandardPic = ystandardPic;
    }

    public List getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String cIds) {
        List list=new ArrayList();
        if(StringUtil.notEmpty(cIds)){
            for(String cid:cIds.split(",")){
                list.add(Long.valueOf(cid));
            }
        }
        this.categoryIds = list;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(String showtimes) {
        this.showtimes = showtimes;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpdateFre() {
        return updateFre;
    }

    public void setUpdateFre(String updateFre) {
        this.updateFre = updateFre;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getSerialType() {
        return serialType;
    }

    public void setSerialType(Integer serialType) {
        this.serialType = serialType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTelevision() {
        return television;
    }

    public void setTelevision(String television) {
        this.television = television;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminals) {
        List list=new ArrayList();
        if(StringUtil.notEmpty(terminals)){
            for(String t:terminals.split(",")){
                list.add(Long.valueOf(t));
            }
        }
        this.terminal = list;
    }

    public Long getNewVideoId() {
        return newVideoId;
    }

    public void setNewVideoId(Long newVideoId) {
        this.newVideoId = newVideoId;
    }

    public Long getOldVideoId() {
        return oldVideoId;
    }

    public void setOldVideoId(Long oldVideoId) {
        this.oldVideoId = oldVideoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNewVideoInstallments() {
        return newVideoInstallments;
    }

    public void setNewVideoInstallments(String newVideoInstallments) {
        this.newVideoInstallments = newVideoInstallments;
    }

    public String getOldVideoInstallments() {
        return oldVideoInstallments;
    }

    public void setOldVideoInstallments(String oldVideoInstallments) {
        this.oldVideoInstallments = oldVideoInstallments;
    }

    public Long getIscharge() {
        return ischarge;
    }

    public void setIscharge(Long ischarge) {
        this.ischarge = ischarge;
    }

    public Long getAgeLevel() {
		return ageLevel;
	}

	public void setAgeLevel(Long ageLevel) {
		this.ageLevel = ageLevel;
	}

	public Long getIslive() {
        return islive;
    }

    public void setIslive(Long islive) {
        this.islive = islive;
    }
}
