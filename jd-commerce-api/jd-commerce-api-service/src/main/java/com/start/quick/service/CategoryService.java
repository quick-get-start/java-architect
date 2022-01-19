package com.start.quick.service;

import com.start.quick.entity.Category;
import com.start.quick.model.CategoryItemsModel;
import com.start.quick.model.SubCategoryModel;

import java.util.List;

public interface CategoryService {

    List<Category> findAllRootCategories();

    List<SubCategoryModel> findAllSubCategoriesByRootId(Integer rootId);

    List<CategoryItemsModel> findFreshItemsByCategoryId(Integer rootId);
}
