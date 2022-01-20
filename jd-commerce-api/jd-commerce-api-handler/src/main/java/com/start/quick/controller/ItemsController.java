package com.start.quick.controller;

import com.start.quick.code.ItemResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.domain.ItemCommentsViewModel;
import com.start.quick.entity.Items;
import com.start.quick.entity.ItemsImg;
import com.start.quick.entity.ItemsParam;
import com.start.quick.entity.ItemsSpec;
import com.start.quick.http.ItemInfoResponse;
import com.start.quick.model.CommentLevelCountModel;
import com.start.quick.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("items")
public class ItemsController {

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("info/{itemId}")
    public JSONResult<ItemInfoResponse> info(@PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JSONResult.build(ItemResultCode.INVALID_REQUEST_PARAM, "商品不存在");
        }

        Items item;

        try {
            item = this.itemService.findById(itemId);
        } catch (EntityNotFoundException e) {
            return JSONResult.build(ItemResultCode.INVALID_REQUEST_PARAM, "商品不存在");
        }

        List<ItemsImg> itemImages = this.itemService.findItemImage(itemId);
        List<ItemsSpec> itemSpecs = this.itemService.findItemSpec(itemId);
        ItemsParam itemParam = this.itemService.findItemParam(itemId);

        ItemInfoResponse itemInfo = new ItemInfoResponse();
        itemInfo.setItem(item);
        itemInfo.setItemImages(itemImages);
        itemInfo.setItemSpecs(itemSpecs);
        itemInfo.setItemParam(itemParam);

        return JSONResult.ok("查询商品信息成功", itemInfo);
    }

    @GetMapping("commentLevel")
    public JSONResult<CommentLevelCountModel> commentLevel(@RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JSONResult.build(ItemResultCode.INVALID_REQUEST_PARAM, "商品不存在");
        }

        CommentLevelCountModel countModel = this.itemService.findCommentLevelCount(itemId);
        return JSONResult.ok("查询商品评价成功", countModel);
    }

    @GetMapping("comments")
    public JSONResult<Page<ItemCommentsViewModel>> comments(@RequestParam String itemId,
                                                            @RequestParam(required = false) Integer level,
                                                            @RequestParam(required = false) Integer page,
                                                            @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return JSONResult.build(ItemResultCode.INVALID_REQUEST_PARAM, "商品不存在");
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<ItemCommentsViewModel> result = this.itemService.pageAll(itemId, level, page - 1, pageSize);

        return JSONResult.ok("商品评价查询成功", result);
    }
}
