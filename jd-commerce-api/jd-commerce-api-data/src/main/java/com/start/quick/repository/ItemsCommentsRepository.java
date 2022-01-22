package com.start.quick.repository;

import com.start.quick.domain.ItemCommentsViewModel;
import com.start.quick.domain.StatisticsViewModel;
import com.start.quick.entity.ItemsComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsCommentsRepository extends CrudRepository<ItemsComments, String> {

    @Query(nativeQuery = true, value = "select comment_level as level, count(*) as statistics " +
            "from items_comments " +
            "where item_id = :itemId " +
            "group by comment_level")
    List<StatisticsViewModel> statisticsByItemId(String itemId);

    @Query(nativeQuery = true,
            value = "select ic.comment_level as commentLevel, ic.content as content, ic.spec_name as specName, ic.create_time as createTime, u.avatar as userAvatar, u.nick_name as nickName " +
                    "from items_comments ic " +
                    "left join users u on ic.user_id = u.id " +
                    "where ic.item_id = :itemId " +
                    "order by ic.create_time desc",
            countQuery = "select count(1) from items_comments ic where ic.item_id = :itemId")
    Page<ItemCommentsViewModel> pageAll(String itemId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select ic.comment_level as commentLevel, ic.content as content, ic.spec_name as specName, ic.create_time as createTime, u.avatar as userAvatar, u.nick_name as nickName " +
                    "from items_comments ic " +
                    "left join users u on ic.user_id = u.id " +
                    "where ic.item_id = :itemId and ic.comment_level = :level " +
                    "order by ic.create_time desc",
            countQuery = "select count(1) from items_comments ic where ic.item_id = :itemId and ic.comment_level = :level")
    Page<ItemCommentsViewModel> pageAll(String itemId, Integer level, Pageable pageable);
}
