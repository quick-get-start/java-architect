package com.start.quick.repository;

import com.start.quick.domain.StatisticsViewModel;
import com.start.quick.entity.ItemsComments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsCommentsRepository extends CrudRepository<ItemsComments, String> {

    @Query(nativeQuery = true, value = "select comment_level as level, count(*) as statistics " +
            "from items_comments " +
            "where item_id = :itemId " +
            "group by comment_level")
    List<StatisticsViewModel> statisticsByItemId(String itemId);
}
