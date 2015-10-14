package com.gochinatv.datasync.bean;

import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-1
 * Time:下午5:14
 */
@Entity(value = "channel", noClassnameStored = true)
public class ChannelPo extends BaseBean {

    private Long channelId;
    private String name;
    private String englishName;
    private String standardPic;
    private int status;
    private int displayOrder;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getStandardPic() {
        return standardPic;
    }

    public void setStandardPic(String standardPic) {
        this.standardPic = standardPic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
