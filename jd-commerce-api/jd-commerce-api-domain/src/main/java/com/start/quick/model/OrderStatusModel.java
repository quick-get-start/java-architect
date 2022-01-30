package com.start.quick.model;

public class OrderStatusModel {

    private Integer waitPayCount;

    private Integer waitDeliverCount;

    private Integer waitReceiveCount;

    private Integer waitCommentCount;

    public OrderStatusModel(Integer waitPayCount, Integer waitDeliverCount, Integer waitReceiveCount, Integer waitCommentCount) {
        this.waitPayCount = waitPayCount;
        this.waitDeliverCount = waitDeliverCount;
        this.waitReceiveCount = waitReceiveCount;
        this.waitCommentCount = waitCommentCount;
    }

    public Integer getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(Integer waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public Integer getWaitDeliverCount() {
        return waitDeliverCount;
    }

    public void setWaitDeliverCount(Integer waitDeliverCount) {
        this.waitDeliverCount = waitDeliverCount;
    }

    public Integer getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(Integer waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    public Integer getWaitCommentCount() {
        return waitCommentCount;
    }

    public void setWaitCommentCount(Integer waitCommentCount) {
        this.waitCommentCount = waitCommentCount;
    }
}
