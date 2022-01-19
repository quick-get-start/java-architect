package com.start.quick.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Carousel {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 背景色
     */
    private String backgroundColor;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品分类id
     */
    private String categoryId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    private Integer type;

    /**
     * 轮播图展示顺序
     */
    private Integer sort;

    /**
     * 是否展示
     */
    private Integer isShow;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Calendar createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Calendar updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}