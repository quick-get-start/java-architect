package com.start.quick.domain;

import java.util.Date;

public interface ItemCommentsViewModel {

    Integer getCommentLevel();

    String getContent();

    String getSpecName();

    Date getCreateTime();

    String getUserAvatar();

    String getNickName();
}
