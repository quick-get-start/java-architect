package com.start.quick.domain;

/**
 * 子类别查询视图模型
 */
public class SubCategoryViewModel {

    private Integer id;

    private String name;

    private Integer type;

    private Integer fatherId;

    private Integer subId;

    private String subName;

    private Integer subType;

    private Integer subFatherId;

    public SubCategoryViewModel() {
    }

    public SubCategoryViewModel(Integer id, String name, Integer type, Integer fatherId, Integer subId, String subName, Integer subType, Integer subFatherId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.fatherId = fatherId;
        this.subId = subId;
        this.subName = subName;
        this.subType = subType;
        this.subFatherId = subFatherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getSubFatherId() {
        return subFatherId;
    }

    public void setSubFatherId(Integer subFatherId) {
        this.subFatherId = subFatherId;
    }
}
