package com.gochinatv.datasync.bean;
import com.google.code.morphia.annotations.Entity;

import java.util.List;

/**
 * Created by shhao.
 * Date: 15-4-7
 * Time:上午11:11
 */
@Entity(value = "menu",noClassnameStored = true)
public class MenuPo extends BaseBean {
    private Long menuId;
    private String text;
    private String url;
    private String imgUrl;
    private String imgBrightUrl;
    private int showType;
    private int focusId;
    private Long parentId;
    private String englishName;
    private List<MenuPo> childNode;
    private boolean parentNode;
    private int platform;//1 tv,android_tv
    private int displayOrder;//排序

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgBrightUrl() {
        return imgBrightUrl;
    }

    public void setImgBrightUrl(String imgBrightUrl) {
        this.imgBrightUrl = imgBrightUrl;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getFocusId() {
        return focusId;
    }

    public void setFocusId(int focusId) {
        this.focusId = focusId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public List<MenuPo> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<MenuPo> childNode) {
        this.childNode = childNode;
    }

    public boolean isParentNode() {
        return parentNode;
    }

    public void setParentNode(boolean parentNode) {
        this.parentNode = parentNode;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
