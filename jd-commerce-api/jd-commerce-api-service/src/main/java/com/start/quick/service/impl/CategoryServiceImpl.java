package com.start.quick.service.impl;

import com.start.quick.domain.CategoryItemsViewModel;
import com.start.quick.domain.SubCategoryViewModel;
import com.start.quick.entity.Category;
import com.start.quick.model.CategoryItemsModel;
import com.start.quick.model.ItemsModel;
import com.start.quick.model.SubCategoryModel;
import com.start.quick.repository.CategoryRepository;
import com.start.quick.service.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryItemsModel> findFreshItemsByCategoryId(Integer rootId) {
        Pageable pageable = PageRequest.of(0, 6);
        List<CategoryItemsViewModel> itemsViewModels = this.categoryRepository.pageFreshItemsByCategoryId(rootId, pageable).toList();

        Map<Integer, CategoryItemsModel> map = new HashMap<>();
        for (CategoryItemsViewModel item : itemsViewModels) {
            CategoryItemsModel category = map.get(item.getId());
            if (category == null) {
                category = new CategoryItemsModel(item.getId(), item.getName(), item.getSlogan(), item.getCategoryImage(), item.getBgColor());
            }
            category.getItems().add(new ItemsModel(item.getItemId(), item.getItemName(), item.getItemUrl()));
            map.put(category.getId(), category);
        }

        return new ArrayList<>(map.values());
    }
}
