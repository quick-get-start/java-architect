package com.start.quick.mapper;

import com.start.quick.model.OrderItemModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Select("select " +
            "    item.item_id as itemId, " +
            "    item.item_name as itemName, " +
            "    item.item_img as itemImg, " +
            "    item.item_spec_id as itemSpecId, " +
            "    item.item_spec_name as itemSpecName, " +
            "    item.buy_counts as buyCounts, " +
            "    item.price as price " +
            "from order_items item " +
            "where item.order_id = #{orderId}")
    List<OrderItemModel> findAllByOrderId(String orderId);
}
