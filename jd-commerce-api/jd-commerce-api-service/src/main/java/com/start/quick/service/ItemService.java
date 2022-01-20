package com.start.quick.service;

import com.start.quick.entity.Items;
import com.start.quick.entity.ItemsImg;
import com.start.quick.entity.ItemsParam;
import com.start.quick.entity.ItemsSpec;
import com.start.quick.model.CommentLevelCountModel;
import com.start.quick.model.ItemCommentsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    Items findById(String id);

    List<ItemsImg> findItemImage(String id);

    List<ItemsSpec> findItemSpec(String id);

    ItemsParam findItemParam(String id);

    CommentLevelCountModel findCommentLevelCount(String itemId);

    Page<ItemCommentsModel> pageAll(String itemId, Integer level, Integer page, Integer pageSize);
}