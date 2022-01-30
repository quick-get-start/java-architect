package com.start.quick.mapper;

import com.start.quick.model.OrderModel;
import com.start.quick.mapper.provider.OrderProvider;
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
}
