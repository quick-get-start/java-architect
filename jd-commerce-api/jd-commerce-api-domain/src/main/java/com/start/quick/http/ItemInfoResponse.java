package com.start.quick.http;

import com.start.quick.entity.Items;
import com.start.quick.entity.ItemsImg;
import com.start.quick.entity.ItemsParam;
import com.start.quick.entity.ItemsSpec;

import java.util.List;

public class ItemInfoResponse {

    private Items item;

    private List<ItemsImg> itemImages;

    private List<ItemsSpec> itemSpecs;

    private ItemsParam itemParam;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImages() {
        return itemImages;
    }

    public void setItemImages(List<ItemsImg> itemImages) {
        this.itemImages = itemImages;
    }

    public List<ItemsSpec> getItemSpecs() {
        return itemSpecs;
    }

    public void setItemSpecs(List<ItemsSpec> itemSpecs) {
        this.itemSpecs = itemSpecs;
    }

    public ItemsParam getItemParam() {
        return itemParam;
    }

    public void setItemParam(ItemsParam itemParam) {
        this.itemParam = itemParam;
    }
}
