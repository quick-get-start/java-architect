package com.start.quick.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Items {
    /**
     * 商品主键id
     */
    @Id
    private String id;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 一级分类外键id
     */
    private Integer rootCategoryId;

    /**
     * 累计销售
     */
    private Integer sellCounts;

    /**
     * 上下架状态 1:上架 2:下架
     */
    private Integer onOffStatus;

    /**
     * 商品内容 商品内容
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    @OrderBy
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRootCategoryId() {
        return rootCategoryId;
    }

    public void setRootCategoryId(Integer rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    public Integer getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    public Integer getOnOffStatus() {
        return onOffStatus;
    }

    public void setOnOffStatus(Integer onOffStatus) {
        this.onOffStatus = onOffStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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