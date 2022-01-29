package com.start.quick.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderModel {

    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer payMethod;

    private Integer realPayAmount;

    private Integer postAmount;

    private Integer orderStatus;

    private List<ItemModel> items = new ArrayList<>();

    public OrderModel(String orderId, Date createTime, Integer payMethod, Integer realPayAmount, Integer postAmount, Integer orderStatus) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.payMethod = payMethod;
        this.realPayAmount = realPayAmount;
        this.postAmount = postAmount;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(Integer realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public Integer getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Integer postAmount) {
        this.postAmount = postAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public static class ItemModel {

        private String itemId;

        private String itemName;

        private String itemImg;

        private String itemSpecId;

        private String itemSpecName;

        private Integer buyCounts;

        private Integer price;

        public ItemModel(String itemId, String itemName, String itemImg, String itemSpecId, String itemSpecName, Integer buyCounts, Integer price) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.itemImg = itemImg;
            this.itemSpecId = itemSpecId;
            this.itemSpecName = itemSpecName;
            this.buyCounts = buyCounts;
            this.price = price;
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

        public String getItemImg() {
            return itemImg;
        }

        public void setItemImg(String itemImg) {
            this.itemImg = itemImg;
        }

        public String getItemSpecId() {
            return itemSpecId;
        }

        public void setItemSpecId(String itemSpecId) {
            this.itemSpecId = itemSpecId;
        }

        public String getItemSpecName() {
            return itemSpecName;
        }

        public void setItemSpecName(String itemSpecName) {
            this.itemSpecName = itemSpecName;
        }

        public Integer getBuyCounts() {
            return buyCounts;
        }

        public void setBuyCounts(Integer buyCounts) {
            this.buyCounts = buyCounts;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
