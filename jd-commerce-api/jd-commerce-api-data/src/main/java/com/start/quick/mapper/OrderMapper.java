package com.start.quick.mapper;

import com.start.quick.domain.OrderViewModel;
import com.start.quick.mapper.provider.OrderProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface OrderMapper {

    @SelectProvider(type = OrderProvider.class, method = "findAll")
    List<OrderViewModel> findAll(String userId, Integer status);
}
