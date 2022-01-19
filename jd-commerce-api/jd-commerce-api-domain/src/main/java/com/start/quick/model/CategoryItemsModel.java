package com.start.quick.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemsModel {

    private Integer id;

    private String name;

    private String slogan;

    private String categoryImage;

    private String bgColor;

    private List<ItemsModel> items = new ArrayList<>();

    public CategoryItemsModel() {
    }

    public CategoryItemsModel(Integer id, String name, String slogan, String categoryImage, String bgColor) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.categoryImage = categoryImage;
        this.bgColor = bgColor;
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }
}
