package com.start.quick.controller;

import com.start.quick.code.CategoryResultCode;
import com.start.quick.common.JSONResult;
import com.start.quick.entity.Carousel;
import com.start.quick.entity.Category;
import com.start.quick.enums.YesOrNo;
import com.start.quick.model.CategoryItemsModel;
import com.start.quick.model.SubCategoryModel;
import com.start.quick.service.CarouselService;
import com.start.quick.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {

    private final CarouselService carouselService;
    private final CategoryService categoryService;

    public IndexController(CarouselService carouselService, CategoryService categoryService) {
        this.carouselService = carouselService;
        this.categoryService = categoryService;
    }

    @GetMapping("carousel")
    public JSONResult<List<Carousel>> carousel() {
        List<Carousel> carousels = this.carouselService.findAll(YesOrNo.YES);
        return JSONResult.ok("查询轮播图成功", carousels);
    }

    @GetMapping("categories")
    public JSONResult<List<Category>> categories() {
        List<Category> categories = this.categoryService.findAllRootCategories();
        return JSONResult.ok("查询一级分类成功", categories);
    }

    @GetMapping("subCategories/{rootId}")
    public JSONResult<List<SubCategoryModel>> subCategories(@PathVariable Integer rootId) {
        if (rootId == null) {
            return JSONResult.build(CategoryResultCode.INVALID_REQUEST_PARAM, "分类不存在");
        }

        List<SubCategoryModel> subCategories = this.categoryService.findAllSubCategoriesByRootId(rootId);
        return JSONResult.ok("查询子分类成功", subCategories);
    }

    @GetMapping("freshCategoryItems/{rootId}")
    public JSONResult<List<CategoryItemsModel>> freshCategoryItems(@PathVariable Integer rootId) {
        if (rootId == null) {
            return JSONResult.build(CategoryResultCode.INVALID_REQUEST_PARAM, "分类不存在");
        }

        List<CategoryItemsModel> categories = this.categoryService.findFreshItemsByCategoryId(rootId);
        return JSONResult.ok("查询最新商品成功", categories);
    }
}
