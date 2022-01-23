package com.start.quick.model;

public class ItemsCartModel {

    private String itemId;

    private String itemImgUrl;

    private String itemName;

    private String specId;

    private String specName;

    private Integer priceDiscount;

    private Integer priceNormal;

    public ItemsCartModel() {
    }

    public ItemsCartModel(String itemId, String itemImgUrl, String itemName, String specId, String specName, Integer priceDiscount, Integer priceNormal) {
        this.itemId = itemId;
        this.itemImgUrl = itemImgUrl;
        this.itemName = itemName;
        this.specId = specId;
        this.specName = specName;
        this.priceDiscount = priceDiscount;
        this.priceNormal = priceNormal;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Integer getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
    }
}
