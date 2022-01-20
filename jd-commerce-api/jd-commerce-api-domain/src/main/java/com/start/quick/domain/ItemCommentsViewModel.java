package com.start.quick.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface ItemCommentsViewModel {

    Integer getCommentLevel();

    String getContent();

    String getSpecName();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getCreateTime();

    String getUserAvatar();

    String getNickName();
}
