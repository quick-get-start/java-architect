package com.start.quick.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OrderStatus {
    /**
     * 订单ID;对应订单表的主键id
     */
    @Id
    private String orderId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单创建时间;对应[10:待付款]状态
     */
    private Date createdTime;

    /**
     * 支付成功时间;对应[20:已付款，待发货]状态
     */
    private Date payTime;

    /**
     * 发货时间;对应[30：已发货，待收货]状态
     */
    private Date deliverTime;

    /**
     * 交易成功时间;对应[40：交易成功]状态
     */
    private Date successTime;

    /**
     * 交易关闭时间;对应[50：交易关闭]状态
     */
    private Date closeTime;

    /**
     * 留言时间;用户在交易成功后的留言时间
     */
    private Date commentTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}