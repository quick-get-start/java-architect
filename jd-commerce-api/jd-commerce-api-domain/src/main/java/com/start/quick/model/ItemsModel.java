package com.start.quick.model;

public class ItemsModel {

    private String itemId;

    private String itemName;

    private String itemUrl;

    public ItemsModel() {
    }

    public ItemsModel(String itemId, String itemName, String itemUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
}
