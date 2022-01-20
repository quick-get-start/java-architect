package com.start.quick.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ItemCommentsModel {

    private Integer commentLevel;

    private String content;

    private String specName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String userAvatar;

    private String nickName;

    public ItemCommentsModel() {
    }

    public ItemCommentsModel(Integer commentLevel, String content, String specName, Date createTime, String userAvatar, String nickName) {
        this.commentLevel = commentLevel;
        this.content = content;
        this.specName = specName;
        this.createTime = createTime;
        this.userAvatar = userAvatar;
        this.nickName = nickName;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
