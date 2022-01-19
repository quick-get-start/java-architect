package com.start.quick.service.impl;

import com.start.quick.entity.Category;
import com.start.quick.repository.CategoryRepository;
import com.start.quick.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllRootCategories() {
        return this.categoryRepository.findAllByType(1);
    }
}
