package com.start.quick.mapper;

import com.start.quick.model.CommentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select " +
            "    comment.id as commentId, " +
            "    comment.content as content, " +
            "    comment.create_time as createTime, " +
            "    comment.item_id as itemId, " +
            "    comment.item_name as itemName, " +
            "    comment.spec_name as specName, " +
            "    image.url as itemImg " +
            "from items_comments comment " +
            "left join items_img image on comment.item_id = image.item_id " +
            "where comment.user_id = #{userId} and image.is_main = 1 " +
            "order by comment.create_time desc")
    List<CommentModel> selectAll(String userId);
}
