package com.gochinatv.datasync.bean;

import java.util.ArrayList;
import java.util.List;

import com.gochinatv.datasync.util.StringUtil;
import com.google.code.morphia.annotations.Entity;

/**
 * Created by shhao.
 * Date: 15-4-2
 * Time:下午4:57
 */
@Entity(value = "label_album",noClassnameStored = true)
public class LabelAlbumPo extends BaseBean {
    private long lid;
    private long labelId;
    private long albumId;
    private String labelName;
    private String albumName;
    private int sequnces;
    
    private int copyrightCompeny;
    //终端
    private List terminal;
    
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

	public int getCopyrightCompeny() {
		return copyrightCompeny;
	}

	public void setCopyrightCompeny(int copyrightCompeny) {
		this.copyrightCompeny = copyrightCompeny;
	}

	public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }


    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getSequnces() {
        return sequnces;
    }

    public void setSequnces(int sequnces) {
        this.sequnces = sequnces;
    }
}
