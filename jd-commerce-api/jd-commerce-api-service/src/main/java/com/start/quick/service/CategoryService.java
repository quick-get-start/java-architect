package com.start.quick.service;

import com.start.quick.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllRootCategories();
}
