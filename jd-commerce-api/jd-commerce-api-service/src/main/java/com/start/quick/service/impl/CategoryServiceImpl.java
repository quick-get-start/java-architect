package com.start.quick.service.impl;

import com.start.quick.domain.SubCategoryViewModel;
import com.start.quick.entity.Category;
import com.start.quick.model.SubCategoryModel;
import com.start.quick.repository.CategoryRepository;
import com.start.quick.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> findAllRootCategories() {
        return this.categoryRepository.findAllByType(1);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<SubCategoryModel> findAllSubCategoriesByRootId(Integer rootId) {
        List<SubCategoryViewModel> subCategoryQueryModels = this.categoryRepository.findAllSubCategoriesByRootId(rootId);

        Map<Integer, SubCategoryModel> map = new HashMap<>();
        for (SubCategoryViewModel item : subCategoryQueryModels) {
            SubCategoryModel category = map.get(item.getId());
            if (category == null) {
                category = new SubCategoryModel(item.getId(), item.getName(), item.getType(), item.getFatherId());
            }
            category.getSubCategories().add(new SubCategoryModel(item.getSubId(), item.getSubName(), item.getSubType(), item.getSubFatherId()));
            map.put(category.getId(), category);
        }

        return new ArrayList<>(map.values());
    }
}
