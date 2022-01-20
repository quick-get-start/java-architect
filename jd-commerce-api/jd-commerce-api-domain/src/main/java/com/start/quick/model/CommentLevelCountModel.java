package com.start.quick.model;

public class CommentLevelCountModel {

    private Integer totalCounts = 0;

    private Integer goodCounts = 0;

    private Integer normalCounts = 0;

    private Integer badCounts = 0;

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(Integer goodCounts) {
        this.goodCounts = goodCounts;
    }

    public Integer getNormalCounts() {
        return normalCounts;
    }

    public void setNormalCounts(Integer normalCounts) {
        this.normalCounts = normalCounts;
    }

    public Integer getBadCounts() {
        return badCounts;
    }

    public void setBadCounts(Integer badCounts) {
        this.badCounts = badCounts;
    }

    public Integer computeTotalCounts() {
        return this.goodCounts + this.normalCounts + this.badCounts;
    }
}
