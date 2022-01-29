package com.start.quick.mapper.provider;

import org.apache.commons.lang3.ObjectUtils;

public class OrderProvider {

    public String findAll(String userId, Integer status) {
        String condition = "";
        if (ObjectUtils.isNotEmpty(status)) {
            condition = "and `status`.order_status = #{status} ";
        }
        return "select " +
                "    `order`.id as orderId, " +
                "    `order`.create_time as createTime, " +
                "    `order`.pay_method as payMethod, " +
                "    `order`.real_pay_amount as realPayAmount, " +
                "    `order`.post_amount as postAmount, " +
                "    `status`.order_status as orderStatus, " +
                "    item.item_id as itemId, " +
                "    item.item_name as itemName, " +
                "    item.item_img as itemImg, " +
                "    item.item_spec_id as itemSpecId, " +
                "    item.item_spec_name as itemSpecName, " +
                "    item.buy_counts as buyCounts, " +
                "    item.price as price " +
                "from orders `order` " +
                "left join order_status `status` on `status`.order_id = `order`.id " +
                "left join order_items item on item.order_id = `order`.id " +
                "where `order`.user_id = #{userId} " +
                "and `order`.is_delete = 0 " +
                condition +
                "order by `order`.update_time desc";
    }
}
