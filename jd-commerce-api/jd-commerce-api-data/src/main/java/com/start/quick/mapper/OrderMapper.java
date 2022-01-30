package com.start.quick.mapper;

import com.start.quick.model.OrderModel;
import com.start.quick.mapper.provider.OrderProvider;
import com.start.quick.model.OrderTrendModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @SelectProvider(type = OrderProvider.class, method = "findAll")
    @Results({
            @Result(column = "orderId", property = "orderId"),
            @Result(column = "orderId", property = "items", many = @Many(select = "com.start.quick.mapper.OrderItemMapper.findAllByOrderId"))
    })
    List<OrderModel> findAll(String userId, Integer status);

    @SelectProvider(type = OrderProvider.class, method = "countByStatus")
    Integer countByStatus(String userId, Integer status, Integer isComment);

    @Select("select " +
            "    os.order_id as orderId, " +
            "    os.order_status as orderStatus, " +
            "    os.create_time as createTime, " +
            "    os.pay_time as payTime, " +
            "    os.deliver_time as deliverTime, " +
            "    os.success_time as successTime, " +
            "    os.close_time as closeTime, " +
            "    os.comment_time as commentTime " +
            "from orders o " +
            "left join order_status os on o.id = os.order_id " +
            "where o.is_delete = 0 and os.order_status in (20, 30, 40) and o.user_id = #{userId} " +
            "order by os.order_id desc")
    List<OrderTrendModel> orderTrend(String userId);
}
