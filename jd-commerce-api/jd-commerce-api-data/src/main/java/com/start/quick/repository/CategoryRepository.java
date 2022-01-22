package com.start.quick.repository;

import com.start.quick.domain.CategoryItemsViewModel;
import com.start.quick.domain.SubCategoryViewModel;
import com.start.quick.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findAllByType(Integer type);

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    @Query("select new com.start.quick.domain.SubCategoryViewModel(father.id, father.name, father.type, father.fatherId,child.id, child.name, child.type, child.fatherId) " +
            "from Category father " +
            "left join Category child on child.fatherId = father.id " +
            "where father.fatherId = :rootId")
    List<SubCategoryViewModel> findAllSubCategoriesByRootId(Integer rootId);

    @SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
    @Query(nativeQuery = true,
            value = "select category.id as id, category.name as name, category.slogan as slogan, category.category_image as categoryImage, category.bg_color as bgColor, item.id as itemId, item.item_name as itemName, image.url as itemUrl " +
                    "from category " +
                    "left join items item on category.id = item.root_category_id " +
                    "left join items_img image on item.id = image.item_id " +
                    "where category.type = 1 and image.is_main = 1 " +
                    "and item.root_category_id = :rootId " +
                    "order by item.create_time desc")
    Slice<CategoryItemsViewModel> pageFreshItemsByCategoryId(Integer rootId, Pageable pageable);
}
