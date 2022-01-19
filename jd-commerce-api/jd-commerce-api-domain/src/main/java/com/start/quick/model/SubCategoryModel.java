package com.start.quick.model;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryModel {

    private Integer id;

    private String name;

    private Integer type;

    private Integer fatherId;

    private List<SubCategoryModel> subCategories = new ArrayList<>();

    public SubCategoryModel() {
    }

    public SubCategoryModel(Integer id, String name, Integer type, Integer fatherId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.fatherId = fatherId;
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

    public List<SubCategoryModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryModel> subCategories) {
        this.subCategories = subCategories;
    }
}
