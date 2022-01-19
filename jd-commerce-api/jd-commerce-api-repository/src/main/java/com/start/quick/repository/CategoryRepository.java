package com.start.quick.repository;

import com.start.quick.domain.SubCategoryViewModel;
import com.start.quick.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    @Query(nativeQuery = true, value = "select category.id, category.name, category.slogan, category.category_image, category.bg_color, items.id as item_id, items.item_name, items_img.url " +
            "from category " +
            "left join items on category.id = items.root_category_id " +
            "left join items_img on items.id = items_img.item_id " +
            "where category.type = 1 and items_img.is_main = 1 " +
            "and items.root_category_id = :rootId " +
            "order by items.create_time desc " +
            "limit 6")
    Object[] findFreshItemsByCategoryId(Integer rootId);
}
