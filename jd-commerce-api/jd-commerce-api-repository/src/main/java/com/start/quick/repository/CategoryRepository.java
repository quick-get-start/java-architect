package com.start.quick.repository;

import com.start.quick.domain.SubCategoryViewModel;
import com.start.quick.entity.Category;
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
}
